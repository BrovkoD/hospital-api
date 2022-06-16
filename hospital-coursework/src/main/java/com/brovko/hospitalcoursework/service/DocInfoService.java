package com.brovko.hospitalcoursework.service;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import com.brovko.hospitalcoursework.model.DocInfoPOJO;
import com.brovko.hospitalcoursework.model.UserRolePOJO;
import com.brovko.hospitalcoursework.repository.DocInfoRepository;
import com.brovko.hospitalcoursework.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@Slf4j
public class DocInfoService {

    private final DocInfoRepository docInfoRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public DocInfoService(DocInfoRepository docInfoRepository, UserRoleRepository userRoleRepository) {
        this.docInfoRepository = docInfoRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<DocInfoPOJO> getAllDocInfo() {

        log.info("getAllDocInfo method");

        return docInfoRepository.findAll();
    }

    public DocInfoPOJO getDocInfoById(Long id) throws LogNotFoundException {

        log.info(format("getDocInfoById method for id=%d", id));

        Optional<DocInfoPOJO> docInfoPOJOOptional = docInfoRepository.findById(id);

        if (docInfoPOJOOptional.isEmpty()) {
            throw new LogNotFoundException(format("DocInfo with id=%d is not found", id));
        } else {
            return docInfoPOJOOptional.get();
        }
    }

    public void updateDocInfo(Long id, DocInfoPOJO newDocInfo) throws LogNotFoundException {

        log.info(format("updateDocInfo method for id=%d", id));

        newDocInfo.setDocId(id);

        Optional<DocInfoPOJO> docInfoPOJOOptional = docInfoRepository.findById(id);
        if (docInfoPOJOOptional.isEmpty()) {
            throw new LogNotFoundException(format("DocInfo with docId=%d is not found", id));
        }

        docInfoRepository.save(newDocInfo);
    }

    public Long addDocInfo(Long id, DocInfoPOJO newDocInfo) throws LogNotFoundException, RoleException {

        log.info(format("addDocInfo method for docId=%s", id));

        newDocInfo.setDocId(id);

        Optional<UserRolePOJO> userRolePOJOOptional = userRoleRepository.findById(id);

        if (userRolePOJOOptional.isEmpty()) {
            throw new LogNotFoundException(format("User with id=%s is not found", id));
        }

        if (!userRolePOJOOptional.get().getRoleNameObject().getName().equals("doctor")) {
            throw new RoleException(format("User with id=%d is not a doctor", id));
        }

        docInfoRepository.save(newDocInfo);

        return id;
    }
}

package com.brovko.hospitalcoursework.service;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.model.RoleNamePOJO;
import com.brovko.hospitalcoursework.model.UserPOJO;
import com.brovko.hospitalcoursework.model.UserRolePOJO;
import com.brovko.hospitalcoursework.model.UserView;
import com.brovko.hospitalcoursework.repository.DocInfoRepository;
import com.brovko.hospitalcoursework.repository.RoleNameRepository;
import com.brovko.hospitalcoursework.repository.UserRepository;
import com.brovko.hospitalcoursework.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Slf4j
public class UserService {

    private final RoleNameRepository roleNameRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(RoleNameRepository roleNameRepository, UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.roleNameRepository = roleNameRepository;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    public String getUserById(Long id) throws LogNotFoundException {

        log.info(format("getUserById method for id=%d", id));

        Optional<UserPOJO> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new LogNotFoundException(format("User with id=%d is not found", id));
        } else {
            return user.get().toString();
        }
    }

    public List<UserPOJO> getUsersByRole(String role) {

        log.info(format("getUsersByRole method for %s", role));

        return userRepository.findAll()
                .stream()
                .filter(UserPOJO -> UserPOJO.getRoleName().equals(role))
                .collect(Collectors.toList());
    }

    public void updateUser(Long id, UserPOJO newUser) throws LogNotFoundException {

        log.info(format("updateUser method for id=%d", id));

        newUser.setId(id);

//        authentication.getAuthorities();

        Optional<UserPOJO> user = userRepository.findById(newUser.getId());
        if (user.isEmpty()) {
            throw new LogNotFoundException(format("User with id=%d is not found", newUser.getId()));
        }

        userRepository.save(newUser);
    }

    public Long createUser(UserView userView) throws LogNotFoundException {

        log.info(format("createUser method for email: %s", userView.getEmail()));

        Optional<RoleNamePOJO> roleNameOptional = roleNameRepository.findByName(userView.getRole());

        if (roleNameOptional.isEmpty()) {
            throw new LogNotFoundException(format("Role %s is not found", userView.getRole()));
        }

        UserPOJO newUser = new UserPOJO(null,
                userView.getFirstName(),
                userView.getLastName(),
                userView.getEmail(),
                userView.getPhoneNum(),
                passwordEncoder.encode(userView.getPassword()),
                LocalDateTime.now(),
                null);

        Long id = userRepository.save(newUser).getId();

        UserRolePOJO userRole = new UserRolePOJO(id, roleNameOptional.get());
        userRoleRepository.save(userRole);

        return id;
    }

//    public void deleteUser(Long id) throws LogNotFoundException {
//
//        log.info(format("deleteUser method for id=%d", id));
//
//        try {
//            userRepository.deleteById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new LogNotFoundException(format("User with id=%d is not found", id));
//        }
//    }



}

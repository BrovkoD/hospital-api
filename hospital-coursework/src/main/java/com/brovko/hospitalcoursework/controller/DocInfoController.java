package com.brovko.hospitalcoursework.controller;

import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.exception.RoleException;
import com.brovko.hospitalcoursework.model.DocInfoPOJO;
import com.brovko.hospitalcoursework.service.DocInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class DocInfoController {

    private final DocInfoService docInfoService;

    @Autowired
    public DocInfoController(DocInfoService docInfoService) {
        this.docInfoService = docInfoService;
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/users/doctors/details")
    public ResponseEntity<List<DocInfoPOJO>> getAllDocInfo () {

        return ResponseEntity.ok(docInfoService.getAllDocInfo());
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/users/doctors/{id}/details")
    public ResponseEntity<DocInfoPOJO> getDocInfoById (@PathVariable Long id) throws LogNotFoundException {
        return ResponseEntity.ok(docInfoService.getDocInfoById(id));
    }

    @PutMapping("/users/doctors/{id}/details")
    public ResponseEntity<Void> updateDocInfo(@PathVariable Long id,
                                           @RequestBody DocInfoPOJO newDocInfo) throws LogNotFoundException {

        docInfoService.updateDocInfo(id, newDocInfo);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/doctors/{docId}/details")
    public ResponseEntity<Void> addDocInfo(@PathVariable Long docId,
                                           @RequestBody DocInfoPOJO newDocInfo) throws LogNotFoundException, RoleException {

        Long id = docInfoService.addDocInfo(docId, newDocInfo);

        return ResponseEntity.created(URI.create("/newUser/" + id)).build();
    }
}

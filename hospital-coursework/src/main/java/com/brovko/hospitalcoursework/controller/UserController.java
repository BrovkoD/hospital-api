package com.brovko.hospitalcoursework.controller;

import com.brovko.hospitalcoursework.configuration.security.AuthenticationFacade;
import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.model.UserPOJO;
import com.brovko.hospitalcoursework.model.UserView;
import com.brovko.hospitalcoursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(AuthenticationFacade authenticationFacade, UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUserById (@PathVariable Long id) throws LogNotFoundException {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/users")
    public ResponseEntity<List<UserPOJO>> getUsersByRole (@RequestParam String role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id,
                                           @RequestBody UserPOJO newUser) throws LogNotFoundException {

        userService.updateUser(id, newUser);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserView userView) throws LogNotFoundException {

        Long id = userService.createUser(userView);

        return ResponseEntity.created(URI.create("/newUser/" + id)).build();
    }

//    @PreAuthorize("hasAuthority('admin') or hasAuthority('receptionist')")
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws LogNotFoundException {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}

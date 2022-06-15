package com.brovko.hospitalcoursework.controller;

import com.brovko.hospitalcoursework.configuration.security.AuthenticationFacade;
import com.brovko.hospitalcoursework.exception.LogNotFoundException;
import com.brovko.hospitalcoursework.model.UserPOJO;
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

    private final AuthenticationFacade authenticationFacade;
    private final UserService userService;

    @Autowired
    public UserController(AuthenticationFacade authenticationFacade, UserService userService) {
        this.authenticationFacade = authenticationFacade;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/userById/{id}")
    public ResponseEntity<String> getUserById (@PathVariable Long id) throws LogNotFoundException {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('doctor')")
    @GetMapping("/usersByRole/{role}")
    public ResponseEntity<List<UserPOJO>> getUsersByRole (@PathVariable String role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Void> updateUser(@RequestBody UserPOJO newUser) throws LogNotFoundException {

        Authentication authentication = authenticationFacade.getAuthentication();
        userService.updateUser(newUser, authentication);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/newUser")
    public ResponseEntity<Void> createUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("email") String email,
                                           @RequestParam(value = "phoneNum", required = false) String phoneNum,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String role) throws LogNotFoundException {

        Long id = userService.createUser(firstName,
                lastName,
                email,
                phoneNum,
                password,
                role);

        return ResponseEntity.created(URI.create("/newUser/" + id)).build();
    }

//    @PreAuthorize("hasAuthority('admin') or hasAuthority('receptionist')")
//    @DeleteMapping("/deleteUser/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws LogNotFoundException {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}

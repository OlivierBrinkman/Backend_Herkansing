package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class AppUserEndpointController {

    private final AppUserServiceImplementation service;

    public AppUserEndpointController(AppUserServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/users/all")
    public ResponseEntity<List> getAllUsers() {
        List<AppUser> users = service.getAppUsers();
        return new ResponseEntity<List>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable long id) {
        return new ResponseEntity<>(service.getAppUserById(id), HttpStatus.FOUND);
    }

    @PostMapping("/users/create")
    public ResponseEntity<Long> createUser(@RequestBody AppUser appUser) {
        return new ResponseEntity<>(service.createAppUser(appUser), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Long> updateUserById(@RequestBody AppUser appUser, @PathVariable long id) {
        appUser.setId(id);
        return new ResponseEntity<>(service.createAppUser(appUser), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable long id) {
        service.deleteAppUser(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }

}

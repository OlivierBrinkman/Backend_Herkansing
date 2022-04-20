package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
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
    public List<AppUser> getAllUsers() {
        return service.getAppUsers();
    }

    @GetMapping("/users/{id}")
    public AppUser getUserById(@PathVariable long id) {
        return service.getAppUserById(id);
    }

    @PostMapping("/users/create")
    public long createUser(@RequestBody AppUser appUser) {
        return service.createAppUser(appUser);
    }

    @PutMapping("/users/{id}")
    public long updateUserById(@RequestBody AppUser appUser, @PathVariable long id) {
        appUser.setId(id);
        return service.createAppUser(appUser);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable long id) {
        service.deleteAppUser(id);
        return "User has been deleted";
    }

}

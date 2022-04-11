package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserEndpointController {

    //Service initialization
    private final AppUserServiceImplementation service;

    //Constructor
    public AppUserEndpointController(AppUserServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/users/all")
    public List<AppUser> getAllUsers() {
        return service.getAppUsers();
    }

    @PostMapping("/users/create")
    public long createUser(@RequestBody AppUser appUser) {
        return service.createAppUser(appUser);
    }

    @PutMapping("/users/{id}")
    public AppUser updateUser(@RequestBody AppUser appUser, @PathVariable long id) {
        appUser.setId(id);
        AppUser updatedAppUser = service.getAppUserById(service.createAppUser(appUser));
        return updatedAppUser;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        service.deleteAppUser(id);
        return "User have been removed";
    }

}

package com.customermanagment.sims.controller;
import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.model.tables.appUser.AppUserRole;
import com.customermanagment.sims.model.tables.appUser.Roles;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
/**
 * App_User Controller
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Controller
public class AppUserController {

    final AppUserServiceImplementation appUserService;
    long selectedAppUserId = 0;
    long selectedAppUserRoleId = 0;

    /**
     * service constructor
     * @param appUserService
     */
    public AppUserController(AppUserServiceImplementation appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * returns Users
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String indexUsers(Model model) {
        selectedAppUserId = 0;
        model.addAttribute("users",appUserService.getAppUsersForDisplay());
        return "appUser/AppUsers";
    }

    /**
     * navigates to Create page
     * @param model
     * @return
     */
    @GetMapping("/users/new")
    public String newUser(Model model){
        model.addAttribute("newAppUser", new AppUser());
        model.addAttribute("newAppUserRole", new AppUserRole());
        //model.addAttribute("roles", appUserService.getRoles());
        return "appUser/AppUserCreate";
    }

    /**
     * creates App_User
     * @param appUser
     * @param appUserRole
     * @return
     */
    @PostMapping("/users/new")
    public String createUser(@ModelAttribute AppUser appUser, @ModelAttribute AppUserRole appUserRole) {
        for(Roles role : Roles.values()) {
            if(appUserRole.getUserRole() == role.toString()) {
                appUserRole.setUserRole(role.toString());
            }
        }
        long userId  = appUserService.createAppUser(appUser);
        appUserRole.setUserId(userId);
        appUserService.createUserRole(appUserRole);
        return "redirect:/users";
    }

    /**
     * deletes App_User
     * @param userId
     * @return
     */
    @RequestMapping(value = "/users/delete" ,method = RequestMethod.GET)
    public String deleteUser(@RequestParam(name="appUserId")long userId) {
        try { appUserService.deleteUserRole(appUserService.getRoleByAppUserId(userId).getId());}
        catch(Exception e) { }
        appUserService.deleteAppUser(userId);
        return "redirect:/users";
    }

    /**
     * navigates to Update screen
     * @param appUserId
     * @param model
     * @return
     */
    @GetMapping(value = "/users/edit")
    public String selectUser(@RequestParam(name="appUserId")long appUserId, Model model) {
        if(appUserId == 0) { return "redirect:/users"; }
        selectedAppUserId = appUserId;
        selectedAppUserRoleId = appUserService.getRoleByAppUserId(appUserId).getId();
        AppUser appUserToEdit = appUserService.getAppUserById(appUserId);

        model.addAttribute("selectedAppUser", appUserToEdit);
        model.addAttribute("selectedAppUserRole", appUserService.getRoleByAppUserId(appUserId));

        System.out.println(appUserId);

        return "appUser/AppUserUpdate";
    }

    /**
     * updates App_User
     * @param appUser
     * @param appUserRole
     * @param result
     * @return
     */
    @PostMapping(value = "/users/edit/{appUserId}", consumes = "application/x-www-form-urlencoded")
    public String updateUser(AppUser appUser, AppUserRole appUserRole, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/users";
        }
        appUser.setId(selectedAppUserId);
        appUserRole.setId(selectedAppUserRoleId);
        appUserRole.setUserId(selectedAppUserId);
        appUserService.createAppUser(appUser);
        appUserService.createUserRole(appUserRole);
        return "redirect:/users";
    }
}

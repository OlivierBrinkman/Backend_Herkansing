package com.customermanagment.sims.service.appUser;

import com.customermanagment.sims.WebSecurityConfig;
import com.customermanagment.sims.model.structures.AppUserStructure;
import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.model.tables.appUser.AppUserRole;
import com.customermanagment.sims.repository.appUser.AppUserRepository;
import com.customermanagment.sims.repository.appUser.AppUserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * App_User Service implementation.
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Service
public class AppUserServiceImplementation implements AppUserService {

    final AppUserRepository appUserRepository;
    final AppUserRoleRepository appUserRoleRepository;

    /**
     * service constructor
     * @param appUserRepository
     * @param appUserRoleRepository
     */
    public AppUserServiceImplementation(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
    }

    /**
     * get all app users
     *
     * @return
     */
    @Override
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    /**
     * get app user by id
     * @param appUserId
     * @return
     */
    @Override
    public AppUser getAppUserById(long appUserId) {
        return appUserRepository.findById(appUserId).get();
    }

    /**
     * get role by app user id
     * @param appUserId
     * @return
     */
    @Override
    public AppUserRole getRoleByAppUserId(long appUserId) {
        AppUserRole local_AppUserRole = new AppUserRole();

        for (AppUserRole appUserRole : appUserRoleRepository.findAll()) {
            if (appUserRole.getUserId() == appUserId) {
                local_AppUserRole = appUserRole;
            }
        }

        return local_AppUserRole;
    }

    /**
     * get app users in structure for display
     *
     * @return
     */
    @Override
    public List<AppUserStructure> getAppUsersForDisplay() {
        List<AppUserStructure> appUserDisplayList = new ArrayList<>();

        for (AppUser appUser : appUserRepository.findAll()) {
            appUser.setPassword(passwordEncoder(appUser.getPassword()));
            AppUserStructure tempAppUserDisplay = new AppUserStructure(appUser, getRoleByAppUserId(appUser.getId()));
            appUserDisplayList.add(tempAppUserDisplay);
        }

        return appUserDisplayList;
    }

    /**
     * creates app user
     * @param appUser
     * @return
     */
    @Override
    public long createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser).getId();
    }

    /**
     * creates user role
     * @param userRole
     */
    @Override
    public void createUserRole(AppUserRole userRole) {
        appUserRoleRepository.save(userRole);
    }

    /**
     * deletes app user by id
     * @param userId
     */
    @Override
    public void deleteAppUser(long userId) {
        appUserRepository.deleteById(userId);
    }

    /**
     * delets user role by id
     * @param userRoleId
     */
    @Override
    public void deleteUserRole(long userRoleId) {
        appUserRoleRepository.deleteById(userRoleId);
    }

    /**
     * password encoder
     * @param password
     * @return
     */
    @Override
    public String passwordEncoder(String password) {
        WebSecurityConfig webConfig = new WebSecurityConfig();
        String encodedPassword = webConfig.passwordEncoder().encode(password);
        return encodedPassword;
    }

}
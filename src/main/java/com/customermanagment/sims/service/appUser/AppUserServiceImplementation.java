package com.customermanagment.sims.service.appUser;

import com.customermanagment.sims.WebSecurityConfig;
import com.customermanagment.sims.model.appUser.AppUser;
import com.customermanagment.sims.model.appUser.AppUserRole;
import com.customermanagment.sims.repository.appUser.AppUserRepository;
import com.customermanagment.sims.repository.appUser.AppUserRoleRepository;
import com.customermanagment.sims.structures.AppUserStructure;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserServiceImplementation implements AppUserService {

    final AppUserRepository appUserRepository;

    final AppUserRoleRepository appUserRoleRepository;

    public AppUserServiceImplementation(AppUserRepository appUserRepository, AppUserRoleRepository appUserRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appUserRoleRepository = appUserRoleRepository;
    }

    @Override
    public List<AppUser> getAppUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser getAppUserById(long appUserId) {
        return appUserRepository.findById(appUserId).get();
    }

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

    @Override
    public long createAppUser(AppUser appUser) {
        return appUserRepository.save(appUser).getId();
    }

    @Override
    public void createUserRole(AppUserRole userRole) {
        appUserRoleRepository.save(userRole);
    }

    @Override
    public String deleteAppUser(long userId) {
        appUserRepository.deleteById(userId);
        return "User has been deleted";
    }

    @Override
    public void deleteUserRole(long userRoleId) {
        appUserRoleRepository.deleteById(userRoleId);
    }

    @Override
    public String passwordEncoder(String password) {
        WebSecurityConfig webConfig = new WebSecurityConfig();
        String encodedPassword = webConfig.passwordEncoder().encode(password);
        return encodedPassword;
    }
}
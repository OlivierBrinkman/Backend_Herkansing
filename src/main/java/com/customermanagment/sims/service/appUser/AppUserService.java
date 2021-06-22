package com.customermanagment.sims.service.appUser;

import com.customermanagment.sims.model.structures.AppUserStructure;
import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.model.tables.appUser.AppUserRole;

import java.util.List;

/**
 * App_User Service
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
public interface AppUserService {

    AppUser getAppUserById(long appUserId);

    AppUserRole getRoleByAppUserId(long appUserId);

    List<AppUser> getAppUsers();

    List<AppUserStructure> getAppUsersForDisplay();

    void deleteAppUser(long userId);

    void createUserRole(AppUserRole userRole);

    void deleteUserRole(long userRoleId);

    long createAppUser(AppUser appUser);

    String passwordEncoder(String password);

}
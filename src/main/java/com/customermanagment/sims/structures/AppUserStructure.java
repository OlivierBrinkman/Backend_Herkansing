package com.customermanagment.sims.structures;

import com.customermanagment.sims.model.appUser.AppUser;
import com.customermanagment.sims.model.appUser.AppUserRole;

public class AppUserStructure {

    public AppUser appUser;

    public AppUserRole appUserRole;

    public AppUserStructure(AppUser appUser, AppUserRole appUserRole) {
        this.appUser = appUser;
        this.appUserRole = appUserRole;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    @Override
    public String toString() {
        return "App_User_Display{" +
                "appUser=" + appUser +
                ", appUserRole=" + appUserRole +
                '}';
    }
}
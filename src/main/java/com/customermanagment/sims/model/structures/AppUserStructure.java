package com.customermanagment.sims.model.structures;
import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.model.tables.appUser.AppUserRole;

/**
 * App_User_Display Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
public class AppUserStructure {

    //Attributes
    public AppUser appUser;

    public AppUserRole appUserRole;

    //Constructors
    public AppUserStructure(AppUser appUser, AppUserRole appUserRole) {
        this.appUser = appUser;
        this.appUserRole = appUserRole;
    }

    //Getters and Setters
    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    //To string
    @Override
    public String toString() {
        return "App_User_Display{" +
                "appUser=" + appUser +
                ", appUserRole=" + appUserRole +
                '}';
    }
}
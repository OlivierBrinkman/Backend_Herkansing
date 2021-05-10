package com.customermanagment.sims.utility.unit;

import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.model.tables.appUser.AppUserRole;
import com.customermanagment.sims.model.tables.appUser.Roles;
import junit.framework.Assert;
import org.junit.Test;

public class AppUserTest {

    @Test
    public void testAppUser() {

        //Creates AppUser
        AppUser appUser = new AppUser();
        appUser.setId(123);
        appUser.setUsername("John Doe");
        appUser.setPassword("Password123");

        //Creates AppUserRole
        AppUserRole appUserRole = new AppUserRole();
        appUserRole.setId(987);
        appUserRole.setUserRole(Roles.CUSTOMER.name());
        appUserRole.setUserId(appUser.getId());

        Assert.assertEquals(123, appUser.getId());
    }

}

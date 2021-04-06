package com.customermanagment.sims.repository.appUser;
import com.customermanagment.sims.model.tables.appUser.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * App_User_Role Repository
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {
}

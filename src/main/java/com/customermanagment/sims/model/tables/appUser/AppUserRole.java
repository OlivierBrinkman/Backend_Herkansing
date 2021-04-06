package com.customermanagment.sims.model.tables.appUser;
import javax.persistence.*;
import java.io.Serializable;
/**
 * App_User_Role Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Entity
@Table(name = "USER_ROLES")
public class AppUserRole implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "ID") private long id;
    @Column(name = "USER_ID", nullable = false) private long userId;
    @Column(name = "USER_ROLE",nullable = false) private String userRole;

    public AppUserRole(long id, long userId, String userRole) {
        this.id = id;
        this.userId = userId;
        this.userRole = userRole;
    }
    public AppUserRole() {}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    @Override
    public String toString() {
        return "App_User_Role{" +
                "id=" + id +
                ", userId=" + userId +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}

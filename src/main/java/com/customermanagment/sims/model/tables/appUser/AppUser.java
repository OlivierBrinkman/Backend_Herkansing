package com.customermanagment.sims.model.tables.appUser;
import javax.persistence.*;
/**
 * App_User Entity
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Entity
@Table(name = "USERS")
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "ID") private long id;
    @Column(name = "USERNAME") private String username;
    @Column(name = "PASSWORD") private String password;

    public AppUser(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public AppUser() { }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "App_User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

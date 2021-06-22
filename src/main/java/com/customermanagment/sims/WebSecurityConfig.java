package com.customermanagment.sims;
import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Web security configurations
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Configuration @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    AppUserServiceImplementation appUserService;

    /**
     * stores the app users that are made in runtime in memory
     * @param auth
     * @throws Exception
     */
    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
        for(AppUser appUser : appUserService.getAppUsers()) {
            auth.inMemoryAuthentication().withUser(appUser.getUsername()).password(passwordEncoder().encode(appUser.getPassword())).roles(appUserService.getRoleByAppUserId(appUser.getId()).getUserRole());
        }
    }

    /**
     * configures http request and processing actions
     * @param http
     * @throws Exception
     */
    @Override protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/downloads/**").permitAll()
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/customers/**").hasRole("ADMIN")
                .antMatchers("/inventory/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/products/**").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/orders/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login_secure").permitAll()
                .and()
                .csrf().disable()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }

    /**
     * sets authentication manager
     * @return
     * @throws Exception
     */
    @Override @Bean public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * encodes password
     * @return
     */
    @Bean public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}

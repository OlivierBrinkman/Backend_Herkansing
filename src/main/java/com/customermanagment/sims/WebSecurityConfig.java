package com.customermanagment.sims;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    AppUserServiceImplementation appUserService;
    @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Override protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/downloads/**").permitAll()
                .antMatchers("/users/**").permitAll()//.hasRole("ADMIN") => TIJDELIJK
                .antMatchers("/brands/**").permitAll()//.hasRole("ADMIN") => TIJDELIJK
                .antMatchers("/customers/**").permitAll()//.hasRole("ADMIN") => TIJDELIJK
                .antMatchers("/inventory/**").permitAll()//.hasAnyRole("ADMIN", "EMPLOYEE") => TIJDELIJK
                .antMatchers("/products/**").permitAll()//.hasAnyRole("ADMIN", "EMPLOYEE") => TIJDELIJK
                .antMatchers("/orders/**").permitAll()//.hasRole("ADMIN") => TIJDELIJK
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login_secure").permitAll()
                .and()
                .csrf().disable()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }

    @Bean public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}

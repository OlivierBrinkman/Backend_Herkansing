package com.customermanagment.sims.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Main Controller
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
@Controller
public class MainController {


    /**
     * return home page
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        return initializeIndex(model);
    }

    /**
     * logs user out
     * @return
     */
    @PostMapping("/logout")
    public String logout(){
        return "Login";
    }

    /**
     * init index
     * @param model
     * @return
     */
    public String initializeIndex(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = ((UserDetails)principal);
        model.addAttribute("userDetails",userDetails );
        return "Index";
    }

    /**
     * returns custom login page
     * @return
     */
    @RequestMapping("/login_secure")
    public String loginIndex() {
        return "Login";
    }


}

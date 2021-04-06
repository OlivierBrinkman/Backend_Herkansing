package com.customermanagment.sims.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exception Controller
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Controller
public class ExceptionController {

    /**
     * 403 exception
     * @return
     */
    @RequestMapping("/403")
    public String NoPermissions() {
        return "redirect:/";
    }

    /**
     * 505 exception
     * @return
     */
    @GetMapping("/505")
    public String ItemNotFound() {
        return "redirect:/";
    }
}

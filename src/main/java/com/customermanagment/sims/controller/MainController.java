package com.customermanagment.sims.controller;

import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import com.customermanagment.sims.service.customer.CustomerServiceImplementation;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
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

    InventoryServiceImplementation inventoryService;
    AppUserServiceImplementation userService;
    CustomerServiceImplementation customerService;
    OrderServiceImplementation orderService;

    public MainController(AppUserServiceImplementation userService, InventoryServiceImplementation inventoryService, CustomerServiceImplementation customerService, OrderServiceImplementation orderService) {
        this.inventoryService = inventoryService;
        this.customerService = customerService;
        this.orderService = orderService;
        this.userService = userService;
    }

    /**
     * return home page
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("orders", orderService.getOrders().size());
        model.addAttribute("customers", customerService.getAllCustomers().size());

        model.addAttribute("brands", inventoryService.getBrands().size());
        model.addAttribute("value", inventoryService.calculateInventoryValue());
        model.addAttribute("users", userService.getAppUsers().size());

        return initializeIndex(model);
    }


    /**
     * logs user out
     *
     * @return
     */
    @PostMapping("/logout")
    public String logout() {
        return "Login";
    }

    /**
     * init index
     * @param model
     * @return
     */
    public String initializeIndex(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = ((UserDetails) principal);
        model.addAttribute("userDetails", userDetails);
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
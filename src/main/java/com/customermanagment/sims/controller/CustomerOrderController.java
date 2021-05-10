package com.customermanagment.sims.controller;
import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.customer.CustomerAddress;
import com.customermanagment.sims.service.customer.CustomerServiceImplementation;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * Customer_Order Controller
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Controller
public class CustomerOrderController {

    int orderProductsListSize = 0;
    int totalOrderPrice = 0;
    long customerId = 0;

    final InventoryServiceImplementation inventoryService;
    final CustomerServiceImplementation customerService;
    final OrderServiceImplementation orderService;

    /**
     * services constructor
     * @param inventoryService
     * @param customerService
     * @param orderService
     */
    public CustomerOrderController(InventoryServiceImplementation inventoryService, CustomerServiceImplementation customerService, OrderServiceImplementation orderService) {
        this.inventoryService = inventoryService;
        this.customerService = customerService;
        this.orderService = orderService;
    }

    /**
     * navigates to customerdetails page for order
     * @param model
     * @return
     */
    @GetMapping("/placeOrder/1")
    public String index(Model model){
        orderProductsListSize = 0;totalOrderPrice = 0;
        model.addAttribute("newCustomer", new Customer());
        model.addAttribute("newCustomerAddress", new CustomerAddress());
        return "order/OrderCreateCustomerDetails";
    }

    /**
     * sets given details as selected customer for step 2
     *
     * @param customer
     * @param customerAddress
     * @param model
     * @return
     */
    @PostMapping("/placeOrder/1")
    public String setCustomerDetails(@ModelAttribute Customer customer, @ModelAttribute CustomerAddress customerAddress, Model model) {
        customerId = customerService.createCustomer(customer);
        customerAddress.setCustomerId(customerId);
        customerService.createCustomerAddress(customerAddress);
        System.out.println(customerId);
        model.addAttribute("newCustomer", customerService.getCustomerById(customerId));
        model.addAttribute("newCustomerAddress", customerService.getCustomerAddressByCustomerId(customerId));
        return "order/OrderCreateCustomerDetails";
    }
}

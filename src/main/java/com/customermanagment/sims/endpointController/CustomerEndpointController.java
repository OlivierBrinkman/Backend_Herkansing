package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.service.customer.CustomerServiceImplementation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerEndpointController {

    //Service initialization
    private final CustomerServiceImplementation service;

    //Constructor
    public CustomerEndpointController(CustomerServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/customers/all")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @PostMapping("/customers/create")
    public long createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable long id) {
        customer.setId(id);
        Customer updatedCustomer = service.getCustomerById(service.createCustomer(customer));
        return updatedCustomer;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomer(@PathVariable long id) {
        service.deleteCustomer(id);
        return "Customer have been removed";
    }

}

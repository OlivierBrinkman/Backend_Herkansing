package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.customer.CustomerAddress;
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

    //Customer Endpoints
    @GetMapping("/customers/all")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return service.getCustomerById(id);
    }

    @PostMapping("/customers/create")
    public long createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    public Customer updateCustomerById(@RequestBody Customer customer, @PathVariable long id) {
        customer.setId(id);
        Customer updatedCustomer = service.getCustomerById(service.createCustomer(customer));
        return updatedCustomer;
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomerById(@PathVariable long id) {
        service.deleteCustomerAddress(id);
        service.deleteCustomer(id);
        return "Customer have been removed";
    }

    //CustomerAddress Endpoints
    @GetMapping("/customers/{id}/address}")
    public CustomerAddress getAddressByCustomerId(@PathVariable long id) {
        return service.getCustomerAddressByCustomerId(id);
    }

    @PostMapping("/customers/{id}/address/create}")
    public long createCustomerAddressById(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @PutMapping("/customers/{id}/address")
    public CustomerAddress updateCustomerAddressById(@RequestBody CustomerAddress customerAddress, @PathVariable long id) {
        customerAddress.setId(id);
        CustomerAddress updatedCustomerAddress = service.getCustomerAddressByCustomerId(service.createCustomerAddress(customerAddress));
        return updatedCustomerAddress;
    }

    @DeleteMapping("/customers/address/{id}")
    public String deleteCustomerAddressById(@PathVariable long id) {
        service.deleteCustomerAddress(id);
        return "Customer Address have been removed";
    }
}

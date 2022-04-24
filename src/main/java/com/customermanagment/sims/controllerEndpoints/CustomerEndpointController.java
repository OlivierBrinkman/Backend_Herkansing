package com.customermanagment.sims.controllerEndpoints;

import com.customermanagment.sims.model.customer.Customer;
import com.customermanagment.sims.model.customer.CustomerAddress;
import com.customermanagment.sims.service.customer.CustomerServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class CustomerEndpointController {

    private final CustomerServiceImplementation service;

    public CustomerEndpointController(CustomerServiceImplementation service) {
        this.service = service;
    }

    @GetMapping("/customers/all")
    public ResponseEntity<List> getAllCustomers() {
        return new ResponseEntity<>(service.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
        return new ResponseEntity<>(service.getCustomerById(id), HttpStatus.FOUND);

    }

    @PostMapping("/customers/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.getCustomerById(service.createCustomer(customer)), HttpStatus.CREATED);

    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomerById(@RequestBody Customer customer, @PathVariable long id) {
        customer.setId(id);
        Customer updatedCustomer = service.getCustomerById(service.createCustomer(customer));
        return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/customers/{id}")
    public String deleteCustomerById(@PathVariable long id) {
        service.deleteCustomerAddress(id);
        service.deleteCustomer(id);
        return "Customer have been removed";
    }

    @GetMapping("/customers/{id}/address")
    public ResponseEntity<CustomerAddress> getAddressByCustomerId(@PathVariable long id) {
        return new ResponseEntity<>(service.getCustomerAddressByCustomerId(id), HttpStatus.FOUND);
    }

    @PostMapping("/customers/{id}/address/create")
    public ResponseEntity<CustomerAddress> createCustomerAddressById(@RequestBody CustomerAddress customerAddress) {
        return new ResponseEntity<>(service.getCustomerAddressByCustomerId(customerAddress.getCustomerId()), HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}/address")
    public ResponseEntity<CustomerAddress> updateCustomerAddressById(@RequestBody CustomerAddress customerAddress, @PathVariable long id) {
        customerAddress.setId(id);
        CustomerAddress updatedCustomerAddress = service.getCustomerAddressByCustomerId(service.createCustomerAddress(customerAddress));
        return new ResponseEntity<CustomerAddress>(updatedCustomerAddress, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/customers/address/{id}")
    public String deleteCustomerAddressById(@PathVariable long id) {
        service.deleteCustomerAddress(id);
        return "Customer Address have been removed";
    }
}

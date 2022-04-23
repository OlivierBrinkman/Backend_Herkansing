package com.customermanagment.sims.service.customer;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.customer.CustomerAddress;

import java.util.List;
/**
 * Customer Service
 *
 * @author Olivier Brinkman
 * @version 1.0
 * @since 12/02/2019
 */
public interface CustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(long customerId);
    CustomerAddress getCustomerAddressByCustomerId(long customerId);
    long createCustomer(Customer customer);
    long createCustomerAddress(CustomerAddress customerAddress);

    String deleteCustomer(long customerId);

    void deleteCustomerAddress(long customerId);

    void insertCustomers();
    void deleteCustomers();
}
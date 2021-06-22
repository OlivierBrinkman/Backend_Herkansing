package com.customermanagment.sims.service.customer;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.model.tables.customer.CustomerAddress;
import com.customermanagment.sims.repository.customer.CustomerAddressRepository;
import com.customermanagment.sims.repository.customer.CustomerRepository;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import com.customermanagment.sims.utility.Utility;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * Customer Service implementation.
 *
 * @author  Olivier Brinkman
 * @version 1.0
 * @since   12/02/2019
 */
@Service
public class CustomerServiceImplementation implements CustomerService {

    Utility utility = new Utility();

    final CustomerRepository customerRepo;
    final CustomerAddressRepository addressRepo;
    final OrderServiceImplementation orderService;

    /**
     * service constructor
     * @param customerRepo
     * @param addressRepo
     * @param orderService
     */
    public CustomerServiceImplementation(CustomerRepository customerRepo, CustomerAddressRepository addressRepo, OrderServiceImplementation orderService) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.orderService = orderService;
    }

    /**
     * get all customers
     *
     * @return
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    /**
     * get customer by id
     * @param id
     * @return
     */
    @Override
    public Customer getCustomerById(long id) {
        return customerRepo.findById(id).get();
    }

    /**
     * creates customer
     * @param customer
     * @return
     */
    @Override
    public long createCustomer(Customer customer) {
        Date temp_now = java.sql.Date.valueOf(java.time.LocalDate.now());
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String now = DateFor.format(temp_now);

        customer.setCustomerSince(now);
        return customerRepo.save(customer).getId();
    }

    /**
     * deletes customer
     * @param id
     */
    @Override
    public void deleteCustomer(long id) {
        customerRepo.deleteById(id);
    }

    /**
     * creates customer address
     * @param customerAddress
     * @return
     */
    @Override
    public long createCustomerAddress(CustomerAddress customerAddress) {
        return addressRepo.save(customerAddress).getId();
    }

    /**
     * get customer address by customer id
     * @return
     */
    @Override
    public CustomerAddress getCustomerAddressByCustomerId(long customerId) {
        CustomerAddress customerAddresses = new CustomerAddress();

        for (CustomerAddress ca : addressRepo.findAll()) {
            if (ca.getCustomerId() == customerId) {
                customerAddresses = ca;
            }
        }

        return customerAddresses;
    }

    /**
     * inserts dummy customers
     */
    @Override
    public void insertCustomers() {
        utility.insertCustomers(customerRepo, addressRepo);
    }

    /**
     * deletes all customers
     */
    @Override
    public void deleteCustomers() {
        orderService.deleteOrders();
        addressRepo.deleteAll();
        customerRepo.deleteAll();
    }
}
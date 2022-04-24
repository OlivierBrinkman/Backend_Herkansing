package com.customermanagment.sims.service.customer;
import com.customermanagment.sims.model.customer.Customer;
import com.customermanagment.sims.model.customer.CustomerAddress;
import com.customermanagment.sims.repository.customer.CustomerAddressRepository;
import com.customermanagment.sims.repository.customer.CustomerRepository;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
import com.customermanagment.sims.utility.Utility;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    Utility utility = new Utility();

    final CustomerRepository customerRepo;

    final CustomerAddressRepository addressRepo;

    final OrderServiceImplementation orderService;

    public CustomerServiceImplementation(CustomerRepository customerRepo, CustomerAddressRepository addressRepo, OrderServiceImplementation orderService) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.orderService = orderService;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepo.findById(id).get();
    }

    @Override
    public long createCustomer(Customer customer) {
        Date temp_now = java.sql.Date.valueOf(java.time.LocalDate.now());
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String now = DateFor.format(temp_now);
        customer.setCustomerSince(now);
        return customerRepo.save(customer).getId();
    }

    @Override
    public String deleteCustomer(long id) {
        customerRepo.deleteById(id);
        return "Customer has been deleted";
    }

    @Override
    public void deleteCustomerAddress(long id) {
        addressRepo.deleteById(id);
    }

    @Override
    public long createCustomerAddress(CustomerAddress customerAddress) {
        return addressRepo.save(customerAddress).getId();
    }

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

    @Override
    public void insertCustomers() {
        utility.insertCustomers(customerRepo, addressRepo);
    }

    @Override
    public void deleteCustomers() {
        orderService.deleteOrders();
        addressRepo.deleteAll();
        customerRepo.deleteAll();
    }
}
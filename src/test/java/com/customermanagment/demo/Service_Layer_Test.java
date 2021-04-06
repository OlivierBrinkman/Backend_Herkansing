package com.customermanagment.demo;
import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.service.customer.CustomerServiceImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Service_Layer_Test {

    @Autowired
    CustomerServiceImplementation customerService;
    Customer customer = new Customer();
    long customerId = 0;

    public void setCustomer() {
        customer.setName("John");
        customer.setEmail("Test@Test.com");
        customer.setPhone("123123123");
        customer.setCustomerSince("12/02/2021");
    }

    @Test
    public void saveCustomer() {
        setCustomer();
        customerId = customerService.createCustomer(customer);
        assertThat(customerId).isNotZero();
    }

    @Test
    public void deleteCustomer() {
        setCustomer();
        saveCustomer();
        customerService.deleteCustomer(customerId);
    }
}

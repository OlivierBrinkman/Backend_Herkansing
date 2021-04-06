package com.customermanagment.demo;
import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.repository.customer.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Repository_Layer_Test {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void findEmployeeById() {
        String expectedName = "John";

        Customer customer = new Customer();
        customer.setName("John");
        customer.setEmail("john@john.com");

        customerRepository.save(customer);
        Customer found = customerRepository.findById(customer.getId()).get();

        assertThat(found.getName()).isEqualTo(expectedName);
    }
}

package com.customermanagment.demo;
import com.customermanagment.sims.model.tables.customer.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Model_Layer_Test {

        @Test
        public void createCustomer(){
            Customer customer = new Customer(1, "John", "John@live.nl", "123123123", "12/12/2021");
            assertThat(customer).isNotNull();
        }

        @Test
        public void setName() {
            String expectedName = "Doe";
            Customer customer = new Customer(1, "John", "John@live.nl", "123123123", "12/12/2021");
            customer.setName("Doe");
            assertThat(customer.getName()).isEqualTo(expectedName);
        }

        @Test
        public void getEmail(){
            String expectedEmail = "John@live.nl";
            Customer customer = new Customer(1, "John", "John@live.nl", "123123123", "12/12/2021");
            assertThat(customer.getEmail()).isEqualTo(expectedEmail);
        }
}

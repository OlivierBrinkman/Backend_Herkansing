package com.customermanagment.demo;
import com.customermanagment.sims.controller.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class Controller_Layer_Test {

    @Autowired
    CustomerController customerController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(customerController).isNotNull();
    }



}

package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.customer.Customer;
import com.customermanagment.sims.service.customer.CustomerServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerEndpointController.class)
public class CustomerEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImplementation service;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void isMockMvcInContext() {
        assertNotNull(mockMvc);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(service).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(1, "John Doe", "JohnDoe@gmail.com", "0652147787", "01-01-2022");
        Customer customer2 = new Customer(2, "Samantha Doe", "SamanthaDoe@Hotmail.com", "0656477872", "01-01-2021");
        customers.add(customer1);
        customers.add(customer2);
        when(service.getAllCustomers()).thenReturn(customers);
        MvcResult result = mockMvc.perform(get("/customers/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2))).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void getCustomerById() throws Exception {
        Customer customer1 = new Customer(1, "John Doe", "JohnDoe@gmail.com", "0652147787", "01-01-2022");
        when(service.getCustomerById(1)).thenReturn(customer1);
        MvcResult result = mockMvc.perform(get("/customers/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("JohnDoe@gmail.com"))
                .andExpect(jsonPath("$.phone").value("0652147787"))
                .andReturn();
        Assert.assertNotNull(result.getResponse());

    }

    @Test
    public void createCustomer() throws Exception {
        Customer customer1 = new Customer(1, "John Doe", "JohnDoe@gmail.com", "0652147787", "01-01-2022");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/customers/create")
                        .content(asJsonString(customer1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void updateCustomerById() throws Exception {
        Customer updatedCustomer = new Customer(123, "Johnny Doe", "password12345", "0651233458", "12-12-2020");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/customers/" + 123)
                        .content(asJsonString(updatedCustomer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void deleteCustomerById() throws Exception {
        long id = 123;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/customers/" + 123)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAddressByCustomerId() throws Exception {

    }

    @Test
    public void createCustomerAddressById() throws Exception {

    }

    @Test
    public void updateCustomerAddressById() throws Exception {

    }

    @Test
    public void deleteCustomerAddressById() throws Exception {

    }
}
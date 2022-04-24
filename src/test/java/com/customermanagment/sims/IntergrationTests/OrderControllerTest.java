package com.customermanagment.sims.IntergrationTests;

import com.customermanagment.sims.controllerEndpoints.OrderEndpointController;
import com.customermanagment.sims.model.order.Order;
import com.customermanagment.sims.model.product.Product;
import com.customermanagment.sims.service.order.OrderServiceImplementation;
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

@WebMvcTest(OrderEndpointController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImplementation service;

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
    public void getAllOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        Order order1 = new Order(1, 1, 2400, "01-01-2022");
        Order order2 = new Order(2, 1, 2400, "01-01-2022");
        orders.add(order1);
        orders.add(order2);
        when(service.getOrders()).thenReturn(orders);
        MvcResult result = mockMvc.perform(get("/orders/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2))).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void createOrder() throws Exception {
        Order order1 = new Order(1, 1, 2400, "01-01-2022");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/orders/create")
                        .content(asJsonString(order1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void updateOrder() throws Exception {
        Order order1 = new Order(1, 1, 2400, "01-01-2022");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/orders/1")
                        .content(asJsonString(order1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void deleteOrder() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/orders/1"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("Order and associated items have been removed", result.getResponse().getContentAsString());
    }

    @Test
    public void getTotalPriceOrderById() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, 1, "iPhone X", "256GB - Black", 1200, 10);
        Product product2 = new Product(2, 1, "iPhone 12Pro", "512GB - Black", 1200, 10);
        Product product3 = new Product(3, 1, "iPhone 13Max", "1024GB - Black", 1200, 10);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        when(service.getProductsByOrderId(1)).thenReturn(products);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/orders/1/price"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("3600", result.getResponse().getContentAsString());
    }
}
package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.product.Brand;
import com.customermanagment.sims.model.tables.product.Product;
import com.customermanagment.sims.service.inventory.InventoryServiceImplementation;
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

@WebMvcTest(InventoryEndpointController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryServiceImplementation service;

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
    public void getAllBrands() throws Exception {
        List<Brand> brands = new ArrayList<>();
        Brand brand1 = new Brand(1, "Apple");
        Brand brand2 = new Brand(1, "Samsung");
        brands.add(brand1);
        brands.add(brand2);

        when(service.getBrands()).thenReturn(brands);
        MvcResult result = mockMvc.perform(get("/brands/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(2))).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void getBrandById() throws Exception {
        Brand brand1 = new Brand(1, "Apple");
        when(service.getBrandById(1)).thenReturn(brand1);
        MvcResult result = mockMvc.perform(get("/brands/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Apple"))
                .andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void createBrand() throws Exception {
        Brand brand1 = new Brand(1, "Apple");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/brands/create")
                        .content(asJsonString(brand1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void updateBrandById() throws Exception {
        Brand brand1 = new Brand(1, "Apple");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/brands/1")
                        .content(asJsonString(brand1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        Assert.assertNotNull(result.getResponse());

    }

    @Test
    public void deleteBrandById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/brands/1"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("Brand and associated products have been removed", result.getResponse().getContentAsString());
    }

    @Test
    public void getProductsByBrandId() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, 1, "iPhone X", "256GB - Black", 1200, 10);
        Product product2 = new Product(2, 1, "iPhone 12Pro", "512GB - Black", 1200, 10);
        Product product3 = new Product(3, 1, "iPhone 13Max", "1024GB - Black", 1200, 10);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        when(service.getProductsByBrandId(1)).thenReturn(products);
        MvcResult result = mockMvc.perform(get("/brands/1/products"))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(3))).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void createProduct() throws Exception {
        Product product1 = new Product(1, 1, "iPhone X", "256GB - Black", 1200, 10);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/brands/1/products")
                        .content(asJsonString(product1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        Assert.assertNotNull(result.getResponse());
    }

    @Test
    public void updateProductById() throws Exception {
        Product product1 = new Product(1, 1, "iPhone X", "256GB - Black", 1200, 10);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/brands/1/products/1")
                        .content(asJsonString(product1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted()).andReturn();
        Assert.assertNotNull(result.getResponse());

    }

    @Test
    public void deleteProductById() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/brands/1/products/1"))
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("Product have been removed", result.getResponse().getContentAsString());
    }
}
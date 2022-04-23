package com.customermanagment.sims.endpointController;

import com.customermanagment.sims.model.tables.appUser.AppUser;
import com.customermanagment.sims.service.appUser.AppUserServiceImplementation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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

@WebMvcTest(AppUserEndpointController.class)
public class AppUserEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserServiceImplementation service;

    @Test
    void isMockMvcInContext() {
        assertNotNull(mockMvc);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(service).build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getUserById() throws Exception {
        AppUser userToCreate = new AppUser(1, "John Doe", "hallo123");
        when(service.getAppUserById(1)).thenReturn(userToCreate);
        mockMvc.perform(get("/users/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("John Doe"))
                .andExpect(jsonPath("$.password").value("hallo123"));
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .content(asJsonString(new AppUser(123, "Samantha Doe", "password123")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserById() throws Exception {
        long id = 123;
        AppUser user = new AppUser(id, "John Doe", "password123");
        when(service.getAppUserById(1)).thenReturn(user);
        AppUser updatedUser = new AppUser(id, "Johnny Doe", "password12345");
        mockMvc.perform(MockMvcRequestBuilders.put("/users/" + id)
                        .content(asJsonString(updatedUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserById() throws Exception {
        long id = 123;
        when(service.deleteAppUser(123)).thenReturn("Student is deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/" + id))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllUsers() throws Exception {
        List<AppUser> users = new ArrayList<>();
        AppUser user1 = new AppUser(1, "John Doe", "Hallo123");
        AppUser user2 = new AppUser(2, "Samantha Doe", "Hello123");
        users.add(user1);
        users.add(user2);
        when(service.getAppUsers()).thenReturn(users);
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class))).andExpect(jsonPath("$.*", hasSize(2)));
    }
}
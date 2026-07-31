package com.unitedtekinfo.authsecurity.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void accessCustomerWithoutTokenTest() throws Exception {

        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isForbidden());

    }

    @Test
    void accessManagerWithoutTokenTest() throws Exception {

        mockMvc.perform(get("/api/manager"))
                .andExpect(status().isForbidden());

    }

    @Test
    void accessAdminWithoutTokenTest() throws Exception {

        mockMvc.perform(get("/api/admin"))
                .andExpect(status().isForbidden());

    }
}
package com.example.springdoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
public class SwaggerUnitTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void shouldDisplaySwaggerUiPage() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        Assertions.assertTrue(contentAsString.contains("Swagger UI"));
    }
}

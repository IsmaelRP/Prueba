package com.prueba.prices.infraestructure.inbound.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PricesControllerIntegrationTest {

	@Autowired
    private MockMvc mockMvc;

	private String bearerToken;
	private String currentTestUser = "test";
	private String currentPassUser = "pass";
	
	@BeforeEach
    void authenticate() throws Exception {
        MvcResult result = mockMvc.perform(post("/users/login")
        		.contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": \"" + currentTestUser + "\", \"password\": \"" + currentPassUser + "\" }"))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        bearerToken = jsonNode.get("body").get("token").asText();
    }
	
    @Test
    void testPrueba1() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-14T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(35.5))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-14T00:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
 
    @Test
    void testPrueba2() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-14T16:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(25.45))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-14T15:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-06-14T18:30:00"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
    @Test
    void testPrueba3() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-14T21:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(35.5))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-14T00:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
    @Test
    void testPrueba4() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-15T10:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(30.5))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-15T00:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-06-15T11:00:00"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
    @Test
    void testPrueba5() throws Exception {
        mockMvc.perform(get("/prices/findPrice")
        		.header("Authorization", bearerToken)
                .param("product_id", "35455")
                .param("application", "2020-06-16T21:00:00")
                .param("subsidiary_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$.body.productId").value(35455))
		        .andExpect(jsonPath("$.body.brandId").value(1))
		        .andExpect(jsonPath("$.body.price").value(38.95))
		        .andExpect(jsonPath("$.body.startDate").value("2020-06-15T16:00:00"))
		        .andExpect(jsonPath("$.body.endDate").value("2020-12-31T23:59:59"))
		        .andExpect(jsonPath("$.msg").value("Price found"));
    }
    
}

package br.com.Impact.ProductManagementSystem.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductsController productsController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.productsController);
    }

    @Test
    public void shouldGetListOfProductDTo() throws Exception {
        String url = "/Products";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers. status().isOk());
    }

    @Test
    void ShouldGetJsonMapFromService() {
        Map<String, BigDecimal> jsonMap = productsController.getJsonMapFromService();
        Assertions.assertFalse(jsonMap.isEmpty());
        Assertions.assertEquals(jsonMap.size(), 16);
        Assertions.assertTrue(jsonMap.containsKey("CAD") && jsonMap.containsKey("DOGE"));
    }

    @Test
    void getProducts() {

        RestAssuredMockMvc
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/Products")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void PostRouteShouldReturn201() throws Exception {
        URI uri = new URI("/Products");
        String json = "{\"name\":\"Nintendo\"," +
                "\"priceBRL\":\"5000\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }



}
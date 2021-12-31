package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@WebMvcTest(ProductsController.class)
@SpringBootTest
class DeixaAqui {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductsController productsController;

//    @Autowired
//    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.productsController);
    }

    @Test
    void ShouldGetJsonMapFromService() {
        Map<String, BigDecimal> jsonMap = productsController.getJsonMapFromService();
        Assertions.assertFalse(jsonMap.isEmpty());
        Assertions.assertEquals(jsonMap.size(), 16);
        Assertions.assertTrue(jsonMap.containsKey("CAD") && jsonMap.containsKey("DOGE"));
    }
//
//    @Test
//    void getProducts() {
//
////        Mockito.when();
//        RestAssuredMockMvc
//                .given()
//                    .accept(ContentType.JSON)
//                .when()
//                    .get("/Products")
//                .then()
//                    .statusCode(HttpStatus.OK.value());
//    }
//
//    @Test
//    void getProductById() {
//    }
//
//    @Test
//    void PostRouteShouldReturn201() throws Exception {
//        URI uri = new URI("/Products");
//        String json = "{\"name\":\"Nintendo\"," +
//                        "\"priceBRL\":\"5000\"}";
//
//        mockMvc.perform(MockMvcRequestBuilders
//                            .post(uri)
//                            .content(json)
//                            .contentType(MediaType.APPLICATION_JSON))
//                            .andExpect(MockMvcResultMatchers.status().is(201));
//    }
//
//    @Test
//    void PostRouteShouldReturnBody() throws Exception {
//        URI uri = new URI("/Products");
//        String json = "{\"name\":\"Nintendo\"," +
//                        "\"priceBRL\":\"5000\"}";
//
//    }
//
//    @Test
//    void updateProduct() {
//    }
//
//    @Test
//    void deleteProductById() {
//    }
}
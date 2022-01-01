package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
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
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductsController productsController;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(this.productsController);
    }

    @Test
    void ShouldGetJsonMapFromService() {
        Map<String, BigDecimal> jsonMap = productsController.getJsonMapFromService();
        Assertions.assertFalse(jsonMap.isEmpty());
        Assertions.assertEquals(jsonMap.size(), 16);
        Assertions.assertTrue(jsonMap.containsKey("CAD") && jsonMap.containsKey("DOGE"));
    }
    //GET PRODUCT
    @Test
    public void getProductsShouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Products"))
                .andExpect(MockMvcResultMatchers. status().isOk());
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
    void getProductsShouldReturnAListWithSomeObjects() {
        List<ProductDTO> products = productsController.getProducts();
        Assertions.assertFalse(products.isEmpty());
    }
    //GET PRODUCT BY ID
    @Test
    void getProductByIdShouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Products/3"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getProductByIdShouldReturn404WithInvalidID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Products/3091"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    //POST PRODUCT
    @Test
    void postProductsShouldReturn201() throws Exception {
        URI uri = new URI("/Products");
        String json = "{\"name\":\"Nintendo\"," +
                "\"priceBRL\":\"5000\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201));
    }
    //UPDATE PRODUCT
    @Test
    void updateProductShouldReturn200() throws Exception {
        int status = productsController.updateProduct(5L, new Product("Switch", new BigDecimal(5000))).getStatusCode().value();
        Assertions.assertEquals(status, 200);
    }

    @Test
    void updateProductShouldReturn404WithInvalidID() throws Exception {
        int status = productsController.updateProduct(425L, new Product("Switch", new BigDecimal(5000))).getStatusCode().value();
        Assertions.assertEquals(status, 404);
    }
    //DELETE PRODUCT
    @Test
    void deleteProductShouldReturn200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Products/3"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteProductShouldReturn404WithInvalidID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Products/3091"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
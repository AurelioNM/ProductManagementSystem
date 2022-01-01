package br.com.Impact.ProductManagementSystem.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EconomiaAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EconomiaAPIController economiaAPIController;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(this.economiaAPIController);
    }

    @Test
    void getCurrencies() {
        Map<String, BigDecimal> currencies = economiaAPIController.getCurrencies();
        Assertions.assertFalse(currencies.isEmpty());
        Assertions.assertEquals(currencies.size(), 16);
        Assertions.assertTrue(currencies.containsKey("CAD"));
    }

    @Test
    void getCurrencyBySymbolShouldReturn200() {
        int status = economiaAPIController.getCurrencyBySymbol("USD").getStatusCode().value();
        Assertions.assertEquals(status, 200);
    }

    @Test
    void getCurrencyBySymbolShouldReturn404WithInvalidSymbol() {
        int status = economiaAPIController.getCurrencyBySymbol("sadlj").getStatusCode().value();
        Assertions.assertEquals(status, 404);
    }
}
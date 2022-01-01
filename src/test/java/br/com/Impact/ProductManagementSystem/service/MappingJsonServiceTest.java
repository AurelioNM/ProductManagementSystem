package br.com.Impact.ProductManagementSystem.service;

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
class MappingJsonServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MappingJsonService mappingJsonService;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.standaloneSetup(this.mappingJsonService);
    }

    @Test
    void getJsonMapStats() {
        Map<String, BigDecimal> jsonMap = mappingJsonService.getJsonMap();
        Assertions.assertFalse(jsonMap.isEmpty());
        Assertions.assertTrue(jsonMap.containsKey("DOGE"));
        Assertions.assertEquals(jsonMap.size(), 16);
    }
}
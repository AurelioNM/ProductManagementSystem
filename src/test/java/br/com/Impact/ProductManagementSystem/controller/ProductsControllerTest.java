package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import br.com.Impact.ProductManagementSystem.service.MappingJsonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static io.restassured.RestAssured.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductRepository productRepo;

    @Mock
    private MappingJsonService mappingJsonService;

    @InjectMocks
    private ProductsController productsController;

    @Test
    public void shouldGetListOfProductDTo() throws Exception {
//        Mockito.when(productRepo.findAll()).then()
        String url = "/Products";
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers. status().isOk());
    }

    @Test
    void trueMethod() {
        Assertions.assertTrue(true);
    }

}
package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/Currency")
public class CurrencyProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate

    public void getCurrencies() {
        restTemplate.getForObject("https://economia.awesomeapi.com.br/all", )
    }

//    [get] /Currency/ (todas as cotações)
//    [get] /Currency/$symbol (exemplo: BRL, USD, EUR)

}

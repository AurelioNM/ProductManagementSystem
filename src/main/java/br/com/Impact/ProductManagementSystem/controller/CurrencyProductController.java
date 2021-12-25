package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/Currency")
public class CurrencyProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

//    https://economia.awesomeapi.com.br/all
//    [get] /Currency/ (todas as cotações)
//    [get] /Currency/$symbol (exemplo: BRL, USD, EUR)

}

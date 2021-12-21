package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Products/{id}/Currency")
public class CurrencyProductController {

    @Autowired
    private ProductRepository productRepository;

//    [get] /Currency/ (todas as cotações)
//    [get] /Currency/$symbol (exemplo: BRL, USD, EUR)

}

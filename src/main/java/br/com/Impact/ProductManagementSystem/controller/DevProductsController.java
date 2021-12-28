package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.MoneyInfo;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Profile("dev")
@RestController
@RequestMapping("/teste")
public class DevProductsController {

    private String stringJson;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    public DevProductsController() throws Exception {}


    @GetMapping
    public Map<String, MoneyInfo> getMoney() {

        ParameterizedTypeReference<HashMap<String, MoneyInfo>> responseType = new ParameterizedTypeReference<>() {};

        RequestEntity<Void> request = RequestEntity.get("https://economia.awesomeapi.com.br/all").accept(MediaType.APPLICATION_JSON).build();

        Map<String, MoneyInfo> jsonDictionary = restTemplate.exchange(request, responseType).getBody();

        return jsonDictionary;


//        return restTemplate.getForObject("https://economia.awesomeapi.com.br/all", Money.class);
    }
}

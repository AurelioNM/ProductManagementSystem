package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import br.com.Impact.ProductManagementSystem.service.MappingJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/Currency")
public class EconomiaAPIController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MappingJsonService mappingJsonService;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public Map<String, BigDecimal> getCurrencies() {
        return mappingJsonService.getJsonMap();
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<BigDecimal> getCurrencyBySymbol(@PathVariable("symbol") String symbol) {
        String upperSymbol = symbol.toUpperCase();
        Map<String, BigDecimal> jsonMap = mappingJsonService.getJsonMap();

        return jsonMap.containsKey(upperSymbol) ?
                ResponseEntity.ok(jsonMap.get(upperSymbol))
                : ResponseEntity.notFound().build();
    }


}

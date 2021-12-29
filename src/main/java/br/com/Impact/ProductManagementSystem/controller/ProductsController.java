package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.net.URL;
import java.util.*;

@Profile("prod")
@RestController
@RequestMapping("/Products")
public class ProductsController {

    private String stringJson;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    public ProductsController() throws Exception {}

    public String convertJsonToString(String link) throws Exception {
        URL url = new URL(link);
        String temp = "";
        Scanner scan = new Scanner(url.openStream());
        while(scan.hasNext()) {
            temp = scan.nextLine();
        }
        return temp;
    }


    @GetMapping
    @Cacheable(value = "getCurrencies")
    public List<ProductDTO> getProducts() throws Exception {

        this.stringJson = convertJsonToString("https://economia.awesomeapi.com.br/all");
        List<Product> products = productRepository.findAll();
        return ProductDTO.convertToDTO(products, stringJson);
    }

    @GetMapping("/{id}")
    @Cacheable(value = "getCurrencies")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable Long id) throws Exception{

        this.stringJson = convertJsonToString("https://economia.awesomeapi.com.br/all");
        Optional<Product> product = productRepository.findById(id);
        return product
                .map(value -> {
                    try {
                        return ResponseEntity.ok(new ProductDTO(value, stringJson));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "getCurrencies", allEntries = true)
    public ResponseEntity<ProductDTO> postProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) throws Exception {

        productRepository.save(product);

        URI uri = uriBuilder.path("/Products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product, stringJson));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "getCurrencies", allEntries = true)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) throws Exception {

        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product updatedProduct = product.update(id, productRepository);
            return ResponseEntity.ok(new ProductDTO(updatedProduct, stringJson));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "getCurrencies", allEntries = true)
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import br.com.Impact.ProductManagementSystem.service.MappingJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Products")
public class ProductsController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MappingJsonService mappingJsonService;

    public Map<String, BigDecimal> getJsonMapFromService() {
        return mappingJsonService.getJsonMap();
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return ProductDTO.convertToDTO(products, getJsonMapFromService());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(p -> {
            try {
                return ResponseEntity.ok(new ProductDTO(p, getJsonMapFromService()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDTO> postProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) throws Exception {
        productRepository.save(product);

        URI uri = uriBuilder.path("Products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product, getJsonMapFromService()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid Product product) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product updatedProduct = product.update(id, productRepository);
            return ResponseEntity.ok(new ProductDTO(updatedProduct, getJsonMapFromService()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

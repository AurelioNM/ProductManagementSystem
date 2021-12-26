package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/Products")
public class BRLProductController {

    private String stringJson = convertJsonToString("https://economia.awesomeapi.com.br/all");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    public BRLProductController() throws Exception {
    }

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
    public List<ProductDTO> getProducts() {

        List<Product> products = productRepository.findAll();
        return ProductDTO.convertToDTO(products, stringJson);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable Long id) throws Exception{

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
    public ResponseEntity<ProductDTO> postProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder) throws Exception {

        productRepository.save(product);

        URI uri = uriBuilder.path("/Products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product, stringJson));
    }

    @PutMapping("/{id}")
    @Transactional
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
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

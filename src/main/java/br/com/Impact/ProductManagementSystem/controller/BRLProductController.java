package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.model.form.ProductForm;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/Products")
public class BRLProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    private String jsonString(String link) throws IOException {
        URL url = new URL(link);
        String temp = "";
            Scanner scan = new Scanner(url.openStream());
            while(scan.hasNext()) {
                temp = scan.nextLine();
            }
            return temp;
    }

    @GetMapping("/teste")
    public void getProductsWithCurrencies() throws IOException {
        String json = jsonString("https://economia.awesomeapi.com.br/all");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Map<String, String>> map = mapper.readValue(json, Map.class);

        String s = map.get("USD").get("ask");
    }

    @GetMapping
    public List<ProductDTO> getProducts() {

        List<Product> products = productRepository.findAll();
        return ProductDTO.convertToDTO(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        return product
                .map(value -> ResponseEntity.ok(new ProductDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDTO> postProduct(@RequestBody @Valid ProductForm productForm, UriComponentsBuilder uriBuilder) {

        Product product = productForm.convertToProduct();
        productRepository.save(product);

        URI uri = uriBuilder.path("/Products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDTO(product));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductForm productForm) {

        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = productForm.update(id, productRepository);
            return ResponseEntity.ok(new ProductDTO(product));
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

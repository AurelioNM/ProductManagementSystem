package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.model.form.ProductForm;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Products")
public class BRLProductController {

    @Autowired
    private ProductRepository productRepository;


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

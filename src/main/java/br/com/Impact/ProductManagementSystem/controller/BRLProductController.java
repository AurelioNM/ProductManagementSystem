package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
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
    public ProductDTO postProduct(@RequestBody @Valid) {

    }

}

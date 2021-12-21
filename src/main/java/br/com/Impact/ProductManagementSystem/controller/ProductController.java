package br.com.Impact.ProductManagementSystem.controller;

import br.com.Impact.ProductManagementSystem.controller.dto.ProductDTO;
import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return ProductDTO.convertToDTO(products);
    }

}

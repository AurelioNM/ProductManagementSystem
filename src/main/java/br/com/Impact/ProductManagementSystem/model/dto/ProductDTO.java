package br.com.Impact.ProductManagementSystem.model.dto;

import br.com.Impact.ProductManagementSystem.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public static List<ProductDTO> convertToDTO(List<Product> products) {
        return products.stream().map(ProductDTO::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

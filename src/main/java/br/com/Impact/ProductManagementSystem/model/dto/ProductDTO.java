package br.com.Impact.ProductManagementSystem.model.dto;

import br.com.Impact.ProductManagementSystem.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal priceBRL;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.priceBRL = product.getPriceBRL();
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

    public BigDecimal getPriceBRL() {
        return priceBRL;
    }
}

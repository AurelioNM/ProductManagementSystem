package br.com.Impact.ProductManagementSystem.model;

import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Profile;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty @Length(min = 2)
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull @Digits(integer = 15, fraction = 2)
    private BigDecimal priceBRL;
    @Transient
    public static List<String> currenciesList = Arrays.asList("USD", "USDT", "CAD", "GBP", "ARS", "BTC", "LTC", "EUR", "JPY", "CHF", "AUD", "CNY", "ILS", "ETH", "XRP", "DOGE");


    public Product() { }

    public Product(String name, BigDecimal priceBRL) {
        this.name = name;
        this.priceBRL = priceBRL;
    }

    public Product update(Long id, ProductRepository productRepository) {
        Product product = productRepository.getById(id);

        product.setName(this.name);
        product.setPriceBRL(this.priceBRL);
        product.setUpdatedAt(LocalDateTime.now());

        return product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getPriceBRL() {
        return priceBRL;
    }

    public void setPriceBRL(BigDecimal priceBRL) {
        this.priceBRL = priceBRL;
    }
}

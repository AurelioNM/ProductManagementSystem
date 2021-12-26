package br.com.Impact.ProductManagementSystem.model;

import org.hibernate.validator.constraints.Length;

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
    private static List<String> cureenciesList = Arrays.asList("USD", "USDT", "CAD", "GBP", "ARS", "BTC", "LTC", "EUR", "JPY", "CHF", "AUD", "CNY", "ILS", "ETH", "XRP", "DOGE");


    public Product() { }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.priceBRL = priceBRL;
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

package br.com.Impact.ProductManagementSystem.model.form;

import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.repository.ProductRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductForm {

    @NotNull @NotEmpty @Length(min = 2)
    private String name;
    @NotNull @Digits(integer = 15, fraction = 2)
    private BigDecimal price;


    public Product convertToProduct() {
        return new Product(name, price);
    }

    public Product update(Long id, ProductRepository productRepository) {
        Product product = productRepository.getById(id);

        product.setName(this.name);
        product.setPrice(this.price);
        product.setUpdatedAt(LocalDateTime.now());

        return product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}

package br.com.Impact.ProductManagementSystem.model.form;

import br.com.Impact.ProductManagementSystem.model.Product;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductForm {

    @NotNull @NotEmpty
    private String name;
    @NotNull @Digits(integer = 15, fraction = 2)
    private BigDecimal price;

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

    public Product convertToProduct() {
        return new Product(name, price);
    }
}

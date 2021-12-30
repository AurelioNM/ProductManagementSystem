package br.com.Impact.ProductManagementSystem.model.dto;

import br.com.Impact.ProductManagementSystem.model.Product;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile("prod")
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal priceBRL;
    private Map<String, BigDecimal> otherCurrencies = new HashMap<>();


    public ProductDTO(Product product, Map<String, BigDecimal> jsonMap) throws Exception {
        this.id = product.getId();
        this.name = product.getName();
        this.priceBRL = product.getPriceBRL();

        for (String key : jsonMap.keySet()) {
            BigDecimal values = jsonMap.get(key).multiply(this.priceBRL);
            otherCurrencies.put(key, values.setScale(2, RoundingMode.HALF_EVEN));
        }
    }

    public static List<ProductDTO> convertToDTO(List<Product> products, Map<String, BigDecimal> jsonMap){
        return products.stream().map(product -> {
            try {
                return new ProductDTO(product, jsonMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).toList();
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

    public Map<String, BigDecimal> getOtherCurrencies() {
        return otherCurrencies;
    }
}

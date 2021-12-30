package br.com.Impact.ProductManagementSystem.model.dto;

import br.com.Impact.ProductManagementSystem.model.Currencies;
import br.com.Impact.ProductManagementSystem.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Profile("dev")
public class DevProductDTO {

    private Long id;
    private String name;
    private BigDecimal priceBRL;
    private Map<String, BigDecimal> otherCurrencies = new HashMap<>();


    public DevProductDTO(Product product, Map<String, BigDecimal> jsonMap) throws Exception {
        this.id = product.getId();
        this.name = product.getName();
        this.priceBRL = product.getPriceBRL();

        for (String key : jsonMap.keySet()) {
            BigDecimal values = jsonMap.get(key).multiply(this.priceBRL);
            otherCurrencies.put(key, values.setScale(2, RoundingMode.HALF_EVEN));
        }
    }

    public static List<DevProductDTO> convertToDTO(List<Product> products, Map<String, BigDecimal> jsonMap){
        return products.stream().map(product -> {
            try {
                return new DevProductDTO(product, jsonMap);
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

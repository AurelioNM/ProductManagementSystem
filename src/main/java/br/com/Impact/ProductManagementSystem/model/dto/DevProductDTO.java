package br.com.Impact.ProductManagementSystem.model.dto;

import br.com.Impact.ProductManagementSystem.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;

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
    private HashMap<String, BigDecimal> otherCurrencies = new HashMap<>();

    public DevProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.priceBRL = product.getPriceBRL();
    }

    public DevProductDTO(Product product, String json) throws Exception {
        this.id = product.getId();
        this.name = product.getName();
        this.priceBRL = product.getPriceBRL();
        buildMap(json);
    }

    public static List<DevProductDTO> convertToDTO(List<Product> products, String json){
        return products.stream().map(product -> {
            try {
                return new DevProductDTO(product, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).toList();
    }

    private void buildMap(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, String>> map = mapper.readValue(json, Map.class);


        for (String currency : Product.currenciesList) {
            double currencies = Double.parseDouble(map.get(currency).get("ask"));
            BigDecimal bigDecimalValue = new BigDecimal(currencies).multiply(priceBRL);
            otherCurrencies.put(currency, bigDecimalValue.setScale(2, RoundingMode.HALF_EVEN));
        }
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

    public HashMap<String, BigDecimal> getOtherCurrencies() {
        return otherCurrencies;
    }
}

package br.com.Impact.ProductManagementSystem.service;

import br.com.Impact.ProductManagementSystem.model.Product;
import br.com.Impact.ProductManagementSystem.model.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConvertToDTOService {

    public static List<ProductDTO> convertToDTO(List<Product> products, Map<String, BigDecimal> jsonMap){
        return products.stream().map(product -> {
            try {
                return new ProductDTO(product, jsonMap);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }

}

package br.com.Impact.ProductManagementSystem.service;

import br.com.Impact.ProductManagementSystem.model.Currencies;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MappingJsonService {

    @Cacheable(value = "JsonMap")
    public Map<String, BigDecimal> getJsonMap() {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<HashMap<String, Currencies>> responseType = new ParameterizedTypeReference<>() {};
        RequestEntity<Void> request = RequestEntity.get("https://economia.awesomeapi.com.br/all").accept(MediaType.APPLICATION_JSON).build();
        Map<String, Currencies> jsonDictionary = restTemplate.exchange(request, responseType).getBody();

        List<String> jsonKeysList = Objects.requireNonNull(jsonDictionary).keySet().stream().collect(Collectors.toList());

        Map<String, BigDecimal> finalMap = new HashMap<>();
        for (String key : jsonKeysList) {
            finalMap.put(key, jsonDictionary.get(key).getAsk());
        }
        return finalMap;
    }
}

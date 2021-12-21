package br.com.Impact.ProductManagementSystem.repository;

import br.com.Impact.ProductManagementSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

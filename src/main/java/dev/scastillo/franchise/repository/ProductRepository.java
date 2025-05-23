package dev.scastillo.franchise.repository;

import dev.scastillo.franchise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

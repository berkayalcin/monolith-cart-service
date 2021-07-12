package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

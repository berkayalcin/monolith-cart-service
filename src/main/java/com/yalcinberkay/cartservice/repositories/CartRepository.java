package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

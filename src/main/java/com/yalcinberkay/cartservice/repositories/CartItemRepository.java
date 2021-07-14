package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCartId(final Long cartId);
}

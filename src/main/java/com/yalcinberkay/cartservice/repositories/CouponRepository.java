package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(final String code);
}

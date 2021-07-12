package com.yalcinberkay.cartservice.repositories;

import com.yalcinberkay.cartservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}

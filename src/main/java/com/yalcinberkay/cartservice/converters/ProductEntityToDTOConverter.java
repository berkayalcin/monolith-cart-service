package com.yalcinberkay.cartservice.converters;

import com.yalcinberkay.cartservice.entities.Product;
import com.yalcinberkay.cartservice.models.DTOs.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductEntityToDTOConverter implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(final Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .price(product.getPrice())
                .title(product.getTitle())
                .build();
    }
}

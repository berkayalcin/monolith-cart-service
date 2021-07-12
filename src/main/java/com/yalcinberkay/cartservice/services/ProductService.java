package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.converters.ProductEntityToDTOConverter;
import com.yalcinberkay.cartservice.entities.Product;
import com.yalcinberkay.cartservice.models.DTOs.ProductDTO;
import com.yalcinberkay.cartservice.repositories.CategoryRepository;
import com.yalcinberkay.cartservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductEntityToDTOConverter productEntityToDTOConverter;

    public List<ProductDTO> getAll() {
        final var products = productRepository.findAll();
        return products.stream().map(productEntityToDTOConverter::apply)
                .map(this::setCategory)
                .collect(Collectors.toList());
    }

    public ProductDTO setCategory(final ProductDTO product) {
        final var category = categoryService.getById(product.getCategoryId());
        product.setCategory(category);
        return product;
    }
}

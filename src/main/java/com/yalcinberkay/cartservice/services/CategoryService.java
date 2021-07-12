package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.converters.CategoryEntityToDTOConverter;
import com.yalcinberkay.cartservice.exceptions.BusinessException;
import com.yalcinberkay.cartservice.models.DTOs.CategoryDTO;
import com.yalcinberkay.cartservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryEntityToDTOConverter categoryEntityToDTOConverter;

    public List<CategoryDTO> getAll() {
        final var categories = categoryRepository.findAll();
        return categories.stream().map(categoryEntityToDTOConverter::apply)
                .collect(Collectors.toList());
    }

    public CategoryDTO getById(final Long id) {
        final var category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new BusinessException("category.is.not.exists");
        }
        return categoryEntityToDTOConverter.apply(category.get());
    }
}

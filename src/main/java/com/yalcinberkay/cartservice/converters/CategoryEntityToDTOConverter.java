package com.yalcinberkay.cartservice.converters;

import com.yalcinberkay.cartservice.entities.Category;
import com.yalcinberkay.cartservice.models.DTOs.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryEntityToDTOConverter implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(final Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .parentId(category.getParentId())
                .title(category.getTitle())
                .build();
    }
}

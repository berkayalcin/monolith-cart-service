package com.yalcinberkay.cartservice.models.DTOs;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryDTO {
    Long id;
    String title;
    Long parentId;
}

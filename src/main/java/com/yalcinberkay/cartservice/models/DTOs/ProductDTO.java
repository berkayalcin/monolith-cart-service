package com.yalcinberkay.cartservice.models.DTOs;

import com.yalcinberkay.cartservice.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    Long id;
    String title;
    Double price;
    Long categoryId;
    CategoryDTO category;
    Status status = Status.ACTIVE;
}

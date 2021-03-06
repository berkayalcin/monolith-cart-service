package com.yalcinberkay.cartservice.controllers;

import com.yalcinberkay.cartservice.models.DTOs.ProductDTO;
import com.yalcinberkay.cartservice.services.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get products")
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get product")
    public ProductDTO getById(@PathVariable("id") final Long id) {
        return productService.getById(id);
    }
}

package com.hackops.observabilitydemo.controller;

import com.hackops.observabilitydemo.dto.ProductDTO;
import com.hackops.observabilitydemo.service.ProductService;
import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Timed(value = "products.get.all", description = "Time taken to return all products")
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }
}
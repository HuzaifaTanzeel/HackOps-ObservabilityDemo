package com.hackops.observabilitydemo.service;

import com.hackops.observabilitydemo.dto.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<ProductDTO> getAllProducts() {
        logger.info("Fetching all products");
        return Arrays.asList(
                new ProductDTO("1", "Laptop", 999.99),
                new ProductDTO("2", "Phone", 499.99)
        );
    }
}

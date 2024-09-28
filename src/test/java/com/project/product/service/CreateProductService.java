package com.project.product.service;

import com.project.product.Command;
import com.project.product.IProductRepository;
import com.project.product.exception.ProductBadRequestException;
import com.project.product.model.Product;
import com.project.product.model.ProductDto;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<ProductDto, String> {
    private final IProductRepository iProductRepository;

    public CreateProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    public ResponseEntity<String> execute(ProductDto input) throws ProductBadRequestException {
        Product product = input.toProduct();
        if(StringUtils.isBlank(product.getName())) {
            throw new ProductBadRequestException("Name is required");
        }
        int id = iProductRepository.save(product).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Product %d, created", id));
    }
}


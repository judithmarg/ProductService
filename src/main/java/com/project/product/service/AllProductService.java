package com.project.product.service;

import com.project.product.IProductRepository;
import com.project.product.Query;
import com.project.product.model.Product;
import com.project.product.model.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AllProductService implements Query<Void,List<ProductDto>> {
    private final IProductRepository iProductRepository;

    AllProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }
    @Override
    public ResponseEntity<List<ProductDto>> execute(Void input) {
        List<Product> products = iProductRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products.stream().map(ProductDto::new).toList());
    }
}


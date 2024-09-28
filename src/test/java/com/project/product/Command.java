package com.project.product;

import com.project.product.exception.ProductBadRequestException;
import org.springframework.http.ResponseEntity;

public interface Command<I, O> {
    ResponseEntity<O> execute(I input) throws ProductBadRequestException;
}


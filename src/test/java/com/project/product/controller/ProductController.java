package com.project.product.controller;

import com.project.product.model.ProductDto;
import com.project.product.model.UpdateProductDto;
import com.project.product.service.AllProductService;
import com.project.product.service.CreateProductService;
import com.project.product.service.GetProductService;
import com.project.product.service.UpdateProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final AllProductService allProductService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final GetProductService getProductService;

    ProductController(AllProductService allProductService, CreateProductService createProductService,
                      UpdateProductService updateProductService, GetProductService getProductService) {
        this.allProductService = allProductService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
        this.getProductService = getProductService;
    }

    @PostMapping
    public ResponseEntity<String> create(
            @RequestBody ProductDto productDto
    ) {
        return createProductService.execute(productDto);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> obtain(@PathVariable Integer id) {
        return this.getProductService.execute(id);
    }
    @DeleteMapping
    public ResponseEntity<String> delete() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return this.updateProductService.execute(new UpdateProductDto(id, productDto));
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductDto>> index() {
        return this.allProductService.execute(null);
    }

}


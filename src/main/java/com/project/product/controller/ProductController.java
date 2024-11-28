package com.project.product.controller;

import com.project.product.ProductApi;
import com.project.product.model.ProductDto;
import com.project.product.model.UpdateProductDto;
import com.project.product.service.AllProductService;
import com.project.product.service.CreateProductService;
import com.project.product.service.GetProductService;
import com.project.product.service.UpdateProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController implements ProductApi {

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

    @Override
    public ResponseEntity<String> create(
            @Valid @RequestBody ProductDto productDto
    ) {

        return createProductService.execute(productDto);
    }
    @Override
    public ResponseEntity<ProductDto> obtain(@PathVariable Integer id) {
        return this.getProductService.execute(id);
    }
    @DeleteMapping
    public ResponseEntity<String> delete() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Integer id, @Valid @RequestBody ProductDto productDto) {
        return this.updateProductService.execute(new UpdateProductDto(id, productDto));
    }

    @Override
    public ResponseEntity<List<ProductDto>> index() {
        return this.allProductService.execute(null);
    }
    @GetMapping("/test")
    public String test() {
        return "Controller is working!";
    }

}


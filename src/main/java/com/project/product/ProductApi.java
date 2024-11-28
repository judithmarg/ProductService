package com.project.product;

import com.project.product.model.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Products", description = "Manage product APIs")
@RequestMapping("/product")
public interface ProductApi {
    @Operation(
            summary = "List of products",
            description = "The all products"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Successful"
            ),
            @ApiResponse(
                    responseCode = "500", description = "Error",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class))
                    }),
            }
    )
    @GetMapping("/all")
    ResponseEntity<List<ProductDto>> index();
    @Operation(summary = "Get a product by ID", description = "Retrieve a product based on its ID")
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> obtain(Integer id);

    @Operation(summary = "Create a new product", description = "Add a new product to the catalog")
    @PostMapping
    ResponseEntity<String> create(ProductDto product);
}

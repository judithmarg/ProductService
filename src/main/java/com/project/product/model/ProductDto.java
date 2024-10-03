package com.project.product.model;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ProductDto {
    private Integer id;

    @NotNull(message = "Field name can't be null")
    @NotBlank(message = "Field name can't be empty")
    @Size(min = 3, max = 20, message = "The length of product name must be 3 between 20")
    private String name;

    @Size(max = 200, message = "The description is optional")
    private String description;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }

    public Product toProduct() {
        var product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDescription(this.description);
        return product;
    }
}


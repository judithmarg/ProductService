package com.project.product.validator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductValidator {
    @NotNull(message = "Field id can't be null")
    @NotBlank(message = "Field id can't be empty")
    private Integer id;

    @NotNull(message = "Field name can't be null")
    @NotBlank(message = "Field name can't be empty")
    @Size(min = 3, max = 20, message = "The length of product name must be 3 between 20")
    private String name;

    @Size(min = 0, max = 200, message = "The description is optional")
    private String description;

    public @NotNull(message = "Field id can't be null") @NotBlank(message = "Field id can't be empty") Integer getId() {
        return id;
    }

    public void setId(@NotNull(message = "Field id can't be null") @NotBlank(message = "Field id can't be empty") Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Field name can't be null") @NotBlank(message = "Field name can't be empty") @Size(min = 3, max = 20, message = "The length of product name must be 3 between 20") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Field name can't be null") @NotBlank(message = "Field name can't be empty") @Size(min = 3, max = 20, message = "The length of product name must be 3 between 20") String name) {
        this.name = name;
    }

    public @Size(min = 0, max = 200, message = "The description is optional") String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 0, max = 200, message = "The description is optional") String description) {
        this.description = description;
    }
}

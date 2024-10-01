package com.project.product.exception;


import org.springframework.http.HttpStatus;
import com.project.product.model.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        return new ErrorResponse(productNotFoundException.getMessage());
    }

    @ExceptionHandler(ProductBadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductBadRequest(ProductBadRequestException productBadRequestException) {
        return new ErrorResponse(productBadRequestException.getMessage());
    }
}

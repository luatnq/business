package com.example.productservice.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException() {
        super(MessageError.PRODUCT_NOT_FOUND.toString());
    }

}

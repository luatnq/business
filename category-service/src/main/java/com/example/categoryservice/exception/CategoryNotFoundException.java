package com.example.categoryservice.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException() {
        super(MessageError.CATEGORY_NOT_FOUND.toString());
    }

}

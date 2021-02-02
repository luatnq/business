package com.example.reviewsservice.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException() {
        super(MessageError.REVIEW_NOT_FOUND.toString());
    }

}

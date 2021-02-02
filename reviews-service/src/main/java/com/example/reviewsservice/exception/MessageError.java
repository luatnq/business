package com.example.reviewsservice.exception;

import lombok.Getter;

@Getter
public enum MessageError {
    REVIEW_NOT_FOUND(404, "error.api.review-not-found");

    private final int code;
    private final String message;

    MessageError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n" +
                "Exception body:" + "{" + "\n" +
                "\t" + "code: " + code + "," + "\n" +
                "\t" + "message: \"" + message + '\"' + "\n" +
                '}';
    }

}

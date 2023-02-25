package com.shoppingcart.exception;

import org.springframework.http.HttpStatus;

public class ShoppingCartAPIException extends RuntimeException {

	
	private HttpStatus status;
    private String message;

    public ShoppingCartAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ShoppingCartAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

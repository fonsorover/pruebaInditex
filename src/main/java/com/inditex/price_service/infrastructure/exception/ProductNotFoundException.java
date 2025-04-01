package com.inditex.price_service.infrastructure.exception;

class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

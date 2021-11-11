package com.dima.ecommerce.utils;

public class ECommerceException extends Exception{
    String message;

    public ECommerceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

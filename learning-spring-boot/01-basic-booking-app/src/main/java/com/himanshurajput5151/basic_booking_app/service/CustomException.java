package com.himanshurajput5151.basic_booking_app.service;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
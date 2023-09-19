package com.example.transactions.Advice;

@SuppressWarnings("serial")
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
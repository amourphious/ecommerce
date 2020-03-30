package com.example.ecommerce.exception;

public class ConfirmationTokenExpiredException extends RuntimeException
{
    public ConfirmationTokenExpiredException(String message)
    {
        super(message);
    }

    public ConfirmationTokenExpiredException() {

    }
}

package com.example.ecommerce.exception;

public class EmailAlreadyExistsException extends RuntimeException
{

    public EmailAlreadyExistsException(String msg)
    {
        super(msg);
    }
}

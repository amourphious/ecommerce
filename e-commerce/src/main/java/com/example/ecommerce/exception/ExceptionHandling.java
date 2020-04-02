package com.example.ecommerce.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;

@ControllerAdvice
@RestController

public class ExceptionHandling extends ResponseEntityExceptionHandler {

    // exception handler handles the exception thrown at the run time by the controller
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ResponseEntity<Object> handleEmailAlreadyExistsExceptions(EmailAlreadyExistsException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), "Email already Exists",
                request.getDescription(false));
        return new ResponseEntity(apiError, HttpStatus.IM_USED);
    }


    @ExceptionHandler(ConfirmationTokenExpiredException.class)
    public final ResponseEntity<Object> handleTokenNotFoundExceptions(ConfirmationTokenExpiredException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), "Token Expired",
                request.getDescription(false));
        return new ResponseEntity(apiError, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(NotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(apiError, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ApiError apiError = new ApiError(new Date(), "Validation failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity(apiError, HttpStatus.METHOD_NOT_ALLOWED);


    }
}



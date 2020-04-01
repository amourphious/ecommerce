package com.example.ecommerce.controller;


import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.ConfirmationTokenExpiredException;
import com.example.ecommerce.exception.EmailAlreadyExistsException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.services.EmailService;
import com.example.ecommerce.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Calendar;
import java.util.Objects;

@RestController
public class RegistrationController {

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrationService registrationService;


    @PostMapping(path = "/register/seller")
    public ResponseEntity<String> registerSeller(@Valid @RequestBody Seller seller){
        String inputEmail=seller.getEmail();
        if(registrationService.checkDuplicateEmail(inputEmail)){
            throw new EmailAlreadyExistsException(inputEmail+" already exist try new one");
        }

        Seller registeredSeller=registrationService.registerSeller(seller);
        String toemail=registeredSeller.getEmail();
        // sending email for confirmation
        emailService.emailToSeller(toemail);

        // for sending created status
        URI location= UriComponentsBuilder.fromPath("http://localhost:8080/seller")
                .path("/{username}")
                .buildAndExpand(registeredSeller.getUsername())
                   .toUri();
        return new ResponseEntity("Customer Account Registered",HttpStatus.CREATED);
    }

    /////////////////////////////////////////////////////////////////////////////////
    //////////////////////// register customer //////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    @PostMapping(path = "/register/customer")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer){
        String inputEmail=customer.getEmail();
        System.out.println("input email");
        if(registrationService.checkDuplicateEmail(inputEmail)){
            throw new EmailAlreadyExistsException(inputEmail+" already exist try new one");
        }

        Customer registeredCustomer=registrationService.registerCustomer(customer);
        String toemail=registeredCustomer.getEmail();
        String activationToken =registeredCustomer.getActivationToken();
        String path=UriComponentsBuilder
                .fromPath("http://localhost:8080/activate/customer")
                .path("/{token}")
                .buildAndExpand(registeredCustomer.getActivationToken())
                .toUri().toString();

        // sending email for confirmation
        emailService.emailToCustomer(toemail, activationToken,path);

        // for sending created status
        URI location= UriComponentsBuilder.fromPath("http://localhost:8080/customer")
                .path("/{username}")
                .buildAndExpand(registeredCustomer.getUsername())
                .toUri();
        return new  ResponseEntity("Customer Account Registered",HttpStatus.CREATED);
    }

    ///////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////Token Confirmation////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    @PutMapping (path = "/activate/customer/{token}")
    public ResponseEntity<Object> activateSeller(@PathVariable String token){
        Customer registeredCustomer=registrationService.findCustomerByToken(token);
        if(registeredCustomer==null){
            throw  new NotFoundException("activation token invalid or user not registered");
        }


        // check for expiration
        Calendar cal = Calendar.getInstance();
        if ((registeredCustomer.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0)
        {
            System.out.println("activation token expired");
            System.out.println("resending the new one");
            Customer expiredCustomer=registrationService.resendActivationToken(registeredCustomer);

            String toemail=expiredCustomer.getEmail();
            String activationToken =expiredCustomer.getActivationToken();
            String path=UriComponentsBuilder
                    .fromPath("http://localhost:8080/activate/customer")
                    .path("/{token}")
                    .buildAndExpand(expiredCustomer.getActivationToken())
                    .toUri().toString();
            // sending email for confirmation
            emailService.emailToCustomer(toemail, activationToken,path);
            throw new ConfirmationTokenExpiredException("token expired new token has been sent");
        }
        else {
            registrationService.activateCustomer(registeredCustomer);
            return new  ResponseEntity<>("account activated", HttpStatus.ACCEPTED);
        }
    }

    @GetMapping(path = "customer/{email}/resendActivationLink")
    public ResponseEntity<String> resendActivationToken(@PathVariable String email){
        Customer queryCustomer=registrationService.checkCustomerEmail(email);
        if(queryCustomer==null){
            throw new NotFoundException("customer with"+email+"not found");
        }

        System.out.println("activation token expired");
        System.out.println("resending the new one");
        registrationService.resendActivationToken(queryCustomer);

        String toemail=queryCustomer.getEmail();
        String activationToken =queryCustomer.getActivationToken();
        String path=UriComponentsBuilder
                .fromPath("http://localhost:8080/activate/customer")
                .path("/{token}")
                .buildAndExpand(queryCustomer.getActivationToken())
                .toUri().toString();
        // sending email for confirmation
        emailService.emailToCustomer(toemail, activationToken,path);
        return new ResponseEntity<>("new token sended",HttpStatus.OK);
    }

}

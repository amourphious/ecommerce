package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CustomerRegisterDTO;
import com.example.ecommerce.dto.SellerRegisterDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.exception.ConfirmationTokenExpiredException;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.services.EmailService;
import com.example.ecommerce.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
public class RegistrationController {

    @Autowired
    EmailService emailService;

    @Autowired
    RegistrationService registrationService;


    @PostMapping(path = "/register/seller")
    public ResponseEntity<String> registerSeller(@Valid @RequestBody SellerRegisterDTO sellerRegisterDTO) {
        String error=registrationService.checkSellerValidation(sellerRegisterDTO);
        if(error!=null){
            return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
        }
        if (registrationService.registerSeller(sellerRegisterDTO)) {
            return new ResponseEntity<>("seller has been registered check mail", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("seller has not been registered", HttpStatus.BAD_REQUEST);
    }

    //////////////////////// register customer //////////////////////////////////////


    @PostMapping(path = "/register/customer")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody CustomerRegisterDTO customerRegisterDTO) {
        if (registrationService.registerCustomer(customerRegisterDTO)) {
            return new ResponseEntity<>("the customer has been registered confirm email to activate ",
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>("the customer cannot be registered  ",
                HttpStatus.NOT_ACCEPTABLE);
    }

    ////////////////////////////////Token Confirmation////////////////////////////////


    @PutMapping(path = "/activate/customer/{token}")
    public ResponseEntity<String> activateSeller(@PathVariable String token) {
        if (registrationService.findCustomerByToken(token)) {
            if (registrationService.activateCustomer(token)) {
                return new ResponseEntity<>("the account has been activated", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("the token expired check mail for new token", HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new NotFoundException("invalid token");
        }
    }

    @PutMapping(path = "customer/resendActivationLink/{email}")
    public ResponseEntity<String> resendActivationToken(@PathVariable String email) {
        if(registrationService.resendActivationToken(email)){
            return new ResponseEntity<>("the token has been regenerated check mail",HttpStatus.OK);
        }
        return new ResponseEntity<>("the customer with email id not found",HttpStatus.BAD_REQUEST);
    }

}

package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CustomerResponseDTO;
import com.example.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(path ="/customer/list")
    public List<CustomerResponseDTO> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping(path ="/customer/{id}" )
    public CustomerResponseDTO getCustomer(@PathVariable Long id){
        return customerService.getCustomer(id);
    }

    @DeleteMapping(path ="/customer/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable  Long id){
       CustomerResponseDTO customerResponseDTO =customerService.getCustomer(id);
        customerService.deleteCustomer(id);
       return new ResponseEntity("the customer has been deleted", HttpStatus.NO_CONTENT);
    }
}

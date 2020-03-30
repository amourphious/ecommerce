package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @GetMapping(path ="/customer/{username}" )
    public Customer getCustomer(@PathVariable String username){
        Customer customer=customerService.getCustomer(username);
        if(customer==null){
            throw new NotFoundException("customer with "+username+" not found");
        }
        return customer;
    }

    @DeleteMapping(path ="/customer/delete/{username}")
    public ResponseEntity deleteCustomer(@PathVariable  String username){
       Customer customer=customerService.getCustomer(username);
        if(customer==null){
            throw new NotFoundException("customer with "+username+" not found");
        }
        customerService.deleteCustomer(customer);
       return  ResponseEntity.noContent().build();
    }
}

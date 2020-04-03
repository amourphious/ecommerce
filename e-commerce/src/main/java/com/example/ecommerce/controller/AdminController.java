package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CustomerResponseDTO;
import com.example.ecommerce.dto.SellerRegisterDTO;
import com.example.ecommerce.dto.SellerResponseDto;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.services.AdminService;
import com.example.ecommerce.services.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    AdminService adminService;


    @GetMapping(path = "/admin-panel/customer-list")
    public List<CustomerResponseDTO> getAllCustomer(){
        return adminService.getAllCustomer();
    }

    @GetMapping(path = "/admin-panel/seller-list")
    public List<SellerResponseDto> getAllSeller(){
        return adminService.getAllSellers();
    }

    @PutMapping(path = "/admin-panel/activate/customer")
    public ResponseEntity<String> activateCustomer(@RequestParam Long id){
        if(adminService.activateCustomer(id)){
            return new ResponseEntity<String>("Customer has been activated check mail",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Customer is already active",HttpStatus.OK);
    }

    @PutMapping(path = "/admin-panel/deactivate/customer")
    public ResponseEntity<String> deactivateCustomer(@RequestParam Long id){
        if (adminService.deactivateCustomer(id)){
            return new ResponseEntity<String>("customer has been deactivated check mail",HttpStatus.OK);
        }
        return new ResponseEntity<String>("customer is already deactive",HttpStatus.OK);
    }

    @PutMapping(path = "/admin-panel/activate/seller")
    public ResponseEntity<String> activateSeller(@RequestParam Long id){
        if(adminService.activateSeller(id)){
            return new ResponseEntity<String>("Seller has been activated check mail",HttpStatus.OK);
        }
        return new ResponseEntity<String>("Seller is already active",HttpStatus.OK);
    }

    @PutMapping(path = "/admin-panel/deactivate/seller")
    public ResponseEntity<String> deactivateSeller(@RequestParam Long id){
        if(adminService.deactivateSeller(id)){
            return new ResponseEntity<>("Seller has been deactivated check mail",HttpStatus.OK);
        }
        return new ResponseEntity<>("Seller is already deactivated check mail",HttpStatus.OK);
    }

}

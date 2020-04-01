package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.services.AdminService;
import com.example.ecommerce.services.EmailService;
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
    AdminService adminService;

    @Autowired
    EmailService emailService;

    @GetMapping(path = "/admin-panel/customer-list")
    public List<Customer> getAllCustomer(){
        List<Customer> customerList=adminService.getAllCustomer();
        if(customerList==null){
            throw new NotFoundException("there are no customer registered");
        }
        return customerList;
    }

    @GetMapping(path = "/admin-panel/seller-list")
    public List<Seller> getAllSeller(){
        List<Seller> sellerList=adminService.getAllSellers();
        if(sellerList==null){
            throw new NotFoundException("there are no seller registered");
        }
        return sellerList;
    }

    @PutMapping(path = "/admin-panel/activate/customer")
    public ResponseEntity<String> activateCustomer(@RequestParam Long id){
        Customer customer=adminService.checkCustomer(id);
        if(customer==null){
            throw new NotFoundException("customer with "+id+" not found");
        }
        else if(customer.isActive()){
            return new ResponseEntity<>("the customer is already active", HttpStatus.OK);
        }
        else{
            Customer activeCustomer=adminService.activateCustomer(customer);
            String toEmail=activeCustomer.getEmail();
            emailService.statusMail(toEmail,activeCustomer.getFirstName()+"is activated");

            return new ResponseEntity<>("Account activated",HttpStatus.OK);
        }
    }

    @PutMapping(path = "/admin-panel/deactivate/customer")
    public ResponseEntity<String> deactivateCustomer(@RequestParam Long id){
        Customer customer=adminService.checkCustomer(id);
        if(customer==null){
            throw new NotFoundException("customer with "+id+" not found");
        }
        else if(!customer.isActive()){
            return new ResponseEntity<>("the customer is already deactivated", HttpStatus.OK);
        }
        else{
            Customer deactiveCustomer=adminService.deactivateCustomer(customer);
            String toEmail=deactiveCustomer.getEmail();
            emailService.statusMail(toEmail,customer.getFirstName()+" is deactivated");

            return new ResponseEntity<>("Account deactivated",HttpStatus.OK);
        }
    }

    @PutMapping(path = "/admin-panel/activate/seller")
    public ResponseEntity<String> activateSeller(@RequestParam Long id){
        Seller seller=adminService.checkSeller(id);
        if(seller==null){
            throw new NotFoundException("seller with "+id+ "not found");
        }
        else if(seller.isActive()){
            return new ResponseEntity<>("the seller is already active",HttpStatus.OK);
        }
        else{
            Seller activatedSeller=adminService.activateSeller(seller);
            String toEmail=activatedSeller.getEmail();
            emailService.statusMail(toEmail,"seller acount has been activated");
            return new ResponseEntity<>("Account Activated",HttpStatus.OK);
        }
    }

    @PutMapping(path = "/admin-panel/deactivate/seller")
    public ResponseEntity<String> deactivateSeller(@RequestParam Long id){
        Seller seller=adminService.checkSeller(id);
        if(seller==null){
            throw new NotFoundException("seller with "+id+ "not found");
        }
        else if(!seller.isActive()){
            return new ResponseEntity<>("the seller is already deactivated",HttpStatus.OK);
        }
        else{
            Seller deactivatedSeller =adminService.deactivateSeller(seller);
            String toEmail= deactivatedSeller.getEmail();
            emailService.statusMail(toEmail,"seller account has been deactivated");
            return new ResponseEntity<>("Account Deactivated",HttpStatus.OK);
        }
    }
}

package com.example.ecommerce.services;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    // geting seller list
    public List<Seller> getAllSellers(){
        List<Seller> sellerList=sellerRepository.fetchAllSeller();
        if(sellerList==null){
            return null;
        }
        return sellerList;
    }

    // getting customer list
    public  List<Customer> getAllCustomer(){
        List<Customer> customerList=customerRepository.fetchAllCustomer();
        if(customerList==null){
            return null;
        }
        return customerList;
    }

    // check customer
    public Customer checkCustomer(Long id){
        Customer customer=customerRepository.findById(id).get();
        if(customer==null){
            return null;
        }
        return customer;
    }

    //activate customer
    public Customer activateCustomer(Customer customer){
            customer.setActive(true);
            customerRepository.save(customer);
            return customer;
    }

    //deactivate customer
    public Customer deactivateCustomer(Customer customer){
        customer.setActive(false);
        customerRepository.save(customer);
        return customer;
    }


    // check seller
    public Seller checkSeller(Long id){
        Seller seller=sellerRepository.findById(id).get();
        if(seller==null){
            return null;
        }
        return seller;
    }

    //activate customer
    public Seller activateSeller(Seller seller){
        seller.setActive(true);
        sellerRepository.save(seller);
        return seller;
    }

    //deactivate customer
    public Seller deactivateSeller(Seller seller){
        seller.setActive(false);
        sellerRepository.save(seller);
        return seller;
    }
}

package com.example.ecommerce.services;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomer(){
        List<Customer> customerList=customerRepository.fetchAllCustomer();
        return customerList;
    }

    public Customer getCustomer(String username){
        Customer customer=customerRepository.findByUsername(username);
        if(customer==null){
            return null;
        }
        return  customer;
    }

    @Transactional
    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
}


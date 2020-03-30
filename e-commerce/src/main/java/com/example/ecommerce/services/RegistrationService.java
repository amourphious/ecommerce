package com.example.ecommerce.services;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RegistrationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // register seller in deactivate state
    public Seller registerSeller(Seller seller){
        String encodedPassword=passwordEncoder.encode(seller.getPassword());
        seller.setPassword(encodedPassword);
        seller.setRolesList(Arrays.asList(new Role("SELLER_ROLE")));
        seller.setActive(false);
        sellerRepository.save(seller);
        return seller;
    }

    // register seller in deactivate state
    public Customer registerCustomer(Customer customer){
        String encodedPassword=passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customer.setRolesList(Arrays.asList(new Role("CUSTOMER_ROLE")));
        customer.setActive(false);
        customer.setActivationToken(UUID.randomUUID().toString());
        customer.setExpiryDate(1);
        customerRepository.save(customer);
        return customer;
    }

    public Customer resendActivationToken(Customer customer){
        customer.setActive(false);
        customer.setActivationToken(UUID.randomUUID().toString());
        customer.setExpiryDate(1);
        customerRepository.save(customer);
        return customer;
    }

    public void activateCustomer(Customer customer){
        customer.setActive(true);
        customerRepository.save(customer);
    }

    public Customer checkCustomerEmail(String email){
        Customer customer=customerRepository.findByEmail(email);
        if(customer==null){
            return null;
        }
        return customer;
    }
    //
    public Customer findCustomerByToken(String token){
        Customer customer=customerRepository.findByActivationToken(token);
        if(customer==null){
            return null;
        }
        return customer;
    }


    // check for unique email
    public boolean checkDuplicateEmail(String inputEmail){
        List<String> savedEmails=userRepository.fetchAllEmail();
        for (String email:savedEmails) {
            if(email.equals(inputEmail)){
                return true;
            }
        }
        return false;
    }

}

package com.example.ecommerce.services;

import com.example.ecommerce.dto.CustomerRegisterDTO;
import com.example.ecommerce.dto.SellerRegisterDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Seller;

import javax.servlet.http.*;

import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class RegistrationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    ModelMapper modelMapper;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // register seller in deactivate state
    public boolean registerSeller(SellerRegisterDTO sellerRegisterDTO) {
        Seller seller = modelMapper.map(sellerRegisterDTO, Seller.class);
        String encodedPassword = passwordEncoder.encode(seller.getPassword());
        seller.setPassword(encodedPassword);
        seller.setRolesList(Arrays.asList(new Role("SELLER_ROLE")));
        seller.setActive(false);
        sellerRepository.save(seller);
        String toEmail = seller.getEmail();
        emailService.emailToSeller(toEmail);
        return true;
    }

    // register seller in deactivate state
    public boolean registerCustomer(CustomerRegisterDTO customerRegisterDTO) {
        Customer customer = modelMapper.map(customerRegisterDTO, Customer.class);
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customer.setRolesList(Arrays.asList(new Role("CUSTOMER_ROLE")));
        customer.setActive(false);
        customer.setActivationToken(UUID.randomUUID().toString());
        customer.setExpiryDate(2);
        customerRepository.save(customer);
        URI location = UriComponentsBuilder.fromPath("http://localhost:8080/activate/customer")
                .path("/{token}")
                .buildAndExpand(customer.getActivationToken())
                .toUri();
        emailService.emailToCustomer(customer.getEmail(), customer.getActivationToken(), location);
        return true;
    }

    // searching customer by token
    public boolean findCustomerByToken(String token) {
        Customer customer = customerRepository.findByActivationToken(token);
        if(customer==null){
            return false;
        }
        System.out.println(customer.getEmail());
        return true;
    }

    public boolean activateCustomer(String token) {
        Customer registeredCustomer = customerRepository.findByActivationToken(token);
        Calendar cal = Calendar.getInstance();
        if ((registeredCustomer.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            System.out.println("activation token expired");
            System.out.println("resending the new one");
            registeredCustomer.setActive(false);
            registeredCustomer.setActivationToken(UUID.randomUUID().toString());
            registeredCustomer.setExpiryDate(2);
            customerRepository.save(registeredCustomer);
            URI location = UriComponentsBuilder.fromPath("http://localhost:8080/activate/customer")
                    .path("/{token}")
                    .buildAndExpand(registeredCustomer.getActivationToken())
                    .toUri();
            emailService.emailToCustomer(registeredCustomer.getEmail(), registeredCustomer.getActivationToken(), location);
            return false;
        }
        else {
            System.out.println("activating-->"+registeredCustomer.getEmail());
            registeredCustomer.setActive(true);
            registeredCustomer.setActivationToken(null);
            customerRepository.save(registeredCustomer);
            return true;
        }

    }

    public boolean resendActivationToken(String email) {
        System.out.println(email);
        Customer customer=customerRepository.findByEmail("batman@gmail.com");;
        if(customer==null){
            return false;
        }
        customer.setActive(false);
        customer.setActivationToken(UUID.randomUUID().toString());
        customer.setExpiryDate(2);
        customerRepository.save(customer);
        URI location = UriComponentsBuilder.fromPath("http://localhost:8080/activate/customer")
                .path("/{token}")
                .buildAndExpand(customer.getActivationToken())
                .toUri();
        emailService.emailToCustomer(customer.getEmail(), customer.getActivationToken(), location);
        return true;
    }




    public String checkSellerValidation(SellerRegisterDTO sellerRegisterDTO) {
        List<Seller> sellerList = sellerRepository.fetchAllSeller();

        if (!sellerRegisterDTO.getPassword().equals(sellerRegisterDTO.getConfirmPassword())) {
            return "constraint validation error-> password and confirm-password did not match";
        }

        for (Seller seller : sellerList) {
            if (sellerRegisterDTO.getEmail().equals(seller.getEmail())) {
                return "constraint validation error-> email already exist";
            }

            if (sellerRegisterDTO.getUsername().equals(seller.getEmail())) {
                return "constraint validation error-> username already exist";
            }

            if (sellerRegisterDTO.getContactNumber().equals(seller.getContactNumber())) {
                return "constraint validation error-> contactNumber already exist";
            }

            if (sellerRegisterDTO.getGstNumber().equals(seller.getGstNumber())) {
                return "seller account with " + sellerRegisterDTO.getGstNumber() + "already exist";
            }
            if (sellerRegisterDTO.getCompanyName().equals(seller.getCompanyName())) {
                return "seller account with " + sellerRegisterDTO.getCompanyName() + "already exist";
            }
        }

        return null;
    }

}

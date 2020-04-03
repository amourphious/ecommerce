package com.example.ecommerce.services;

import com.example.ecommerce.dto.CustomerResponseDTO;
import com.example.ecommerce.dto.SellerResponseDto;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    ModelMapper modelMapper; // bean created in projectConfiguration.java class

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    // getting customer list
    public  List<CustomerResponseDTO> getAllCustomer() {
        List<Customer> customerList = customerRepository.fetchAllCustomer();
        if (customerList == null) {
            throw new NotFoundException("there are no customers registered");
        }
        Type listType = new TypeToken<List<CustomerResponseDTO>>() {}.getType();
        List<CustomerResponseDTO> customerResponseDTOList=modelMapper.map(customerList,listType);
        return customerResponseDTOList;
    }


    // getting seller list
    public List<SellerResponseDto> getAllSellers(){
        List<Seller> sellerList=sellerRepository.fetchAllSeller();
        if(sellerList==null){
            throw new NotFoundException("there are no seller registered");
        }
        ModelMapper modelMapper=new ModelMapper();
        Type listType=new TypeToken<List<SellerResponseDto>>(){}.getType();
        List<SellerResponseDto> sellerResponseDtos=modelMapper.map(sellerList,listType);
        return sellerResponseDtos;
    }


    //activate customer
    public boolean activateCustomer(Long id){
        Customer customer=customerRepository.findById(id).get();
        if(customer==null){
            throw new NotFoundException("customer with id->"+id+"not found");
        }

        if(customer.isActive()){
            return false;
        }
        customer.setActive(true);
        customerRepository.save(customer);
        String toEmail=customer.getEmail();
        emailService.statusMail(toEmail,"account has been activated");
            return true;
    }

    //deactivate customer
    public boolean deactivateCustomer(Long id){
        Customer customer=customerRepository.findById(id).get();
        if(customer==null){
            throw new NotFoundException("custome with->"+id+"not found");
        }
        if(customer.isActive()){
            customer.setActive(false);
            customerRepository.save(customer);
            String toEmail=customer.getEmail();
            emailService.statusMail(toEmail,"account has been deactivated");
            return true;
        }
        return false;
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
    public boolean activateSeller(Long id){
        Seller seller=sellerRepository.findById(id).get();
        if(seller==null){
            throw new NotFoundException("seller with "+id+"not found");
        }
        if(seller.isActive()){
            return false;
        }
        seller.setActive(true);
        sellerRepository.save(seller);
        String toEmail=seller.getEmail();
        emailService.statusMail(toEmail,"seller account has been activated");
        return true;
    }

    //deactivate customer
    public boolean deactivateSeller(Long id){
        Seller seller=sellerRepository.findById(id).get();
        if(seller==null){
            throw new NotFoundException("seller with "+id+" not found");
        }
        if(seller.isActive()){
            seller.setActive(false);
            sellerRepository.save(seller);
            String toEmail=seller.getEmail();
            emailService.statusMail(toEmail,"seller has been deactivated check mail");
            return true;
        }
        return false;
    }
}

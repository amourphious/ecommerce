package com.example.ecommerce.services;

import com.example.ecommerce.dto.CustomerResponseDTO;
import com.example.ecommerce.dto.SellerResponseDto;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
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

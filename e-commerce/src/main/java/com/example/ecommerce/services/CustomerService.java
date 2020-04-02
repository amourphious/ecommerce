package com.example.ecommerce.services;

import com.example.ecommerce.dto.CustomerResponseDTO;
import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerResponseDTO> getAllCustomer(){
        List<Customer> customerList=customerRepository.fetchAllCustomer();
        ModelMapper modelMapper=new ModelMapper();
        Type listType=new TypeToken<List<CustomerResponseDTO>>() {}.getType();
        List<CustomerResponseDTO> customerResponseDTOList =modelMapper.map(customerList,listType);
        return customerResponseDTOList;
    }

    public CustomerResponseDTO getCustomer(Long id){
        Customer customer=customerRepository.findById(id).get();
        if(customer==null){
            throw  new NotFoundException("user with "+id+"not found");
        }
        ModelMapper modelMapper=new ModelMapper();
        CustomerResponseDTO customerResponseDTO =modelMapper.map(customer, CustomerResponseDTO.class);
        return customerResponseDTO;
    }

    @Transactional
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
}


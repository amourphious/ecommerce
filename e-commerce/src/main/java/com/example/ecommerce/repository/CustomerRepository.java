package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer,Long> {

}

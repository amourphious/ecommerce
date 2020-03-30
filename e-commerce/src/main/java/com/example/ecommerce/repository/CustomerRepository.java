package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer,Long> {

    @Query("from Customer")
    List<Customer> fetchAllCustomer();

    Customer findByEmail(String email);

    Customer findByUsername(String username);

    Customer findByActivationToken(String activationToken);

    @Query("from Seller where activationToken=:activationToken")
    Seller findByToken(@Param("activationToken") String activationToken);
}

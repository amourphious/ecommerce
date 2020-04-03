package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("from Customer")
    List<Customer> fetchAllCustomer();

    //    Optional<Customer> findByEmail(String email);
    Customer findByEmail(String email);

    @Query("from Customer where email=:email")
    Customer fetchByEmail(@Param("email") String email);

    Customer findByUsername(String username);

    Customer findByActivationToken(String activationToken);

    @Query("from Customer where activationToken=:activationToken")
    Customer fetchByToken(@Param("activationToken") String activationToken);
}

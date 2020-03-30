package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);

    User findByUsername(String username);

    @Query("SELECT email from User")
    List<String> fetchAllEmail();
}

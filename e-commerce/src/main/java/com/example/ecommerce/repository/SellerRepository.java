package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Seller;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    @Query("from Seller")
    List<Seller> fetchAllSeller();

    Seller findByUsername(String username);

    Seller findByEmail(String email);

}

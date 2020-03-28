package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Seller;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<Seller,Long> {
}

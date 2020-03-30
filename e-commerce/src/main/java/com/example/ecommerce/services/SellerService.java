package com.example.ecommerce.services;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;


import java.security.PublicKey;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public List<Seller> getAllSellers(){
        List<Seller> sellerList=sellerRepository.fetchAllSeller();
        return sellerList;
    }

    public Seller getSeller(String username){
        Seller seller=sellerRepository.findByUsername(username);
        if(seller==null){
            return null;
        }
        return  seller;
    }

    @Transactional
    public void deleteSeller(Seller seller){
        sellerRepository.delete(seller);
    }
}

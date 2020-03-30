package com.example.ecommerce.controller;


import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping(path = "/seller/list")
    public List<Seller> getAllSellers(){
        List<Seller> sellerList=sellerService.getAllSellers();
        return sellerList;
    }

    @GetMapping(path = "/seller/{username}")
    public Seller getSeller(@PathVariable String username){
        return sellerService.getSeller(username);
    }

    @DeleteMapping(path = "/seller/delete/{username}")
    public ResponseEntity deleteSeller(@PathVariable String username){
        Seller seller=sellerService.getSeller(username);
        if(seller==null){
            throw new NotFoundException(username + "seller not found");
        }
            sellerService.deleteSeller(seller);
        return ResponseEntity.noContent().build();
    }

}

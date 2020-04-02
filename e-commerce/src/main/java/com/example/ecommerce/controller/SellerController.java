package com.example.ecommerce.controller;


import com.example.ecommerce.dto.SellerResponseDto;
import com.example.ecommerce.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {

    @Autowired
    SellerService sellerService;

    @GetMapping(path = "/seller/list")
    public List<SellerResponseDto> getAllSellers(){
        return sellerService.getAllSellers();
    }

    @GetMapping(path = "/seller/{id}")
    public SellerResponseDto getSeller(@PathVariable Long id){
        return sellerService.getSeller(id);
    }

    @DeleteMapping(path = "/seller/delete/{id}")
    public ResponseEntity<String> deleteSeller(@PathVariable Long id){

        SellerResponseDto sellerResponseDto =sellerService.getSeller(id);
        System.out.println(sellerResponseDto.getFirstName());
        sellerService.deleteSeller(sellerResponseDto.getSellerId());
        return  new ResponseEntity<>("seller has been deleted",HttpStatus.NO_CONTENT);
    }

}

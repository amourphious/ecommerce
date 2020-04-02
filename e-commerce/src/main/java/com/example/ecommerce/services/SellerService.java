package com.example.ecommerce.services;

import com.example.ecommerce.dto.SellerResponseDto;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Type;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public List<SellerResponseDto> getAllSellers(){
        List<Seller> sellerList=sellerRepository.fetchAllSeller();
        ModelMapper modelMapper=new ModelMapper();
        Type listType = new TypeToken<List<SellerResponseDto>>() {}.getType();
        List<SellerResponseDto> sellerResponseDtoList =modelMapper.map(sellerList,listType);
        //        List<SellerDto> sellerDtoList = Arrays.asList(modelMapper.map(sellerList, SellerDto[].class));
        return sellerResponseDtoList;
    }

    public SellerResponseDto getSeller(Long id){
        ModelMapper modelMapper=new ModelMapper();
        Seller seller=sellerRepository.findById(id).get();
        if(seller==null){
            throw new NotFoundException("user with "+id+" not found");
        }
        SellerResponseDto sellerResponseDto =modelMapper.map(seller, SellerResponseDto.class);
        return sellerResponseDto;
    }

    @Transactional
    public void deleteSeller(Long id){
        sellerRepository.deleteById(id);
    }
}

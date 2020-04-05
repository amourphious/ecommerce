package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends CrudRepository<Address,Long> {
//    List<Address> findByLabel(String label);

    @Query("from Address a where a.label=:label and a.user.userId=:userId")
    Address fetchAddress(@Param("label") String label,@Param("userId") Long userId);

}

package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Address;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address,Long> {
//    List<Address> findByLabel(String label);

    @Query("from Address a where a.label=:label and a.user.userId=:userId")
    Address fetchAddress(@Param("label") String label,@Param("userId") Long userId);

}

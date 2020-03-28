package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice,Long> {

}

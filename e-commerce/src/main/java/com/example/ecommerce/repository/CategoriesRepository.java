package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories,Long> {

    Categories findByCategoryName(String categoryName);
}

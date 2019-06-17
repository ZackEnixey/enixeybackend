package com.project.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.webapp.dto.response.ProductDto;
import com.project.webapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{


}

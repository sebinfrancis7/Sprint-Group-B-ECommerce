package com.sprint.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}

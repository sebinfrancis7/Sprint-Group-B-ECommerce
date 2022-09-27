package com.sprint.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//	@Query("SELECT SUM(p.rating) FROM Product p WHERE p.prodId =:prodId")
//	public double findProductRating(@Param("prodId") int prodId);
//
//	@Query("SELECT COUNT(p.rating) FROM Product p WHERE p.prodId =:prodId")
//	public int findProductCount(@Param("prodId") int prodId);
}

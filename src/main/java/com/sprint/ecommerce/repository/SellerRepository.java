package com.sprint.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{
	
	@Query("SELECT DISTINCT s.userName FROM Seller s")
	public List<String> uniqueUserName();
	
	@Query("SELECT s FROM Seller s WHERE s.rating >=:rating")
	public List<Seller> findAboveRating(@Param("rating") double rating);
	
//	@Query("SELECT s FROM Seller s WHERE s.sellerName =: sellerName")
//	public List<Seller> findBySellerName(@Param("sellerName") String sellerName);
	
}
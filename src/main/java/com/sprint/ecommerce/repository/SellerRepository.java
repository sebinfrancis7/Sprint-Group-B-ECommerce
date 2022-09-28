package com.sprint.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{
	
	@Query("SELECT s FROM Seller s WHERE s.sellerName =: name")
	public List<Seller> findBySellerName(@Param("name") String sellerName);
	
	@Query("SELECT DISTINCT s.userName FROM Seller s")
	public List<String> uniqueUserName();
	
}

//@Query("SELECT s FROM Seller s where s.userName =: userName")
//public List<Seller> findByUserName(@Param("userName") String userName);

//
//@Query("select case when count(c)> 0 then true else false end from Car c where lower(c.model) like lower(:model)")
//boolean existsCarLikeCustomQuery(@Param("model") String model);
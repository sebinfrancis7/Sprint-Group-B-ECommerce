package com.sprint.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.ecommerce.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer>{

}

package com.sprint.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Seller;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	@Query("select e from Orders e where e.seller=:seller")
	public List<Orders> findBySeller(@Param("seller") Seller seller);
	@Query("select e from Orders e where e.customer=:customer")
	public List<Orders> findByCustomer(@Param("customer") Customer customer);	
}

package com.sprint.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sprint.ecommerce.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{
	@Query("select e from Orders e where e.seller=:id")
	public List<Orders> findBySeller(@Param("id") int id);
}

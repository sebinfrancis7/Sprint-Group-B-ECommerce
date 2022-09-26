package com.sprint.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.ecommerce.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

}

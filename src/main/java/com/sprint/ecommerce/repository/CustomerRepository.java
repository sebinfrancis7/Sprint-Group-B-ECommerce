package com.sprint.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.ecommerce.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Integer>{

	@Query("SELECT DISTINCT c.userName FROM Customer c")
	public List<String> uniqueUserName();
}

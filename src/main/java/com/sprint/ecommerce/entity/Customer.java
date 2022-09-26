package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {

	@Id
	@Column(name="CUST_ID")
	private int custid;
	
	@Column(name="CUST_NAME")
	private String custname;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String address;
	
	private List<Product> wishlist;
	
	private List<Orders> custorders;
	
}

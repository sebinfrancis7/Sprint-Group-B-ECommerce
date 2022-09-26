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
	private int cust_id;
	
	private String cust_name;
	
	@Column(unique=true)
	private String username;
	
	private String password;
	
	private String cust_address;
	
	private List<Product> cust_wishlist;
	
}

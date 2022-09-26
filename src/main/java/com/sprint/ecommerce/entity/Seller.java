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
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Seller {
	
	@Id
	@Column(name="SELLER_ID")
	private int sellerId;
	
	@Column(name="SELLER_NAME")
	private String sellerName; 
	
	@Column(unique = true)
	private String userName;
	
	private String password;
	
	private double rating;
	
	private List<Product> product; 
	
	
}

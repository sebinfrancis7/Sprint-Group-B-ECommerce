package com.sprint.ecommerce.entity;

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
	int seller_id;
	String seller_name; 
	String username;
	String password;
	double rating;
	Product product; 	
	
}

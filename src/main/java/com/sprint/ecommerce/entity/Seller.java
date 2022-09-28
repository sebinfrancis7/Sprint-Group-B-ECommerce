package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

	@Id
	@Column(name = "seller_id")
	private int sellerId;

	@Column(name = "seller_name")
	private String sellerName;

	@Column(unique = true)
	private String userName;
	
	private String password;

	private double rating;

	@OneToMany
	private List<Product> product;

}


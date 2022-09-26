package com.sprint.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@Column(name = "prod_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prodId;
	@Column(name = "prod_name")
	private String prodName;
	private String category;
	private double price;
	private double rating;
}

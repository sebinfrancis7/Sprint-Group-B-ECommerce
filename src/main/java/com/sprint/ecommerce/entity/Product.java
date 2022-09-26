package com.sprint.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Product {
	@Id
	private int prod_id;
	private String prod_name;
	private String category;
	private double price;
	private double rating;
}

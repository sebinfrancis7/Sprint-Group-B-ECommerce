package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Orders {
	@Id
	private int order_id;
	@OneToOne
	private Customer customer;
	@OneToOne
	private Seller seller;
	@ManyToMany
	private List<Product> product;
}

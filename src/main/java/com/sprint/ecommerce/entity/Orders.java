package com.sprint.ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	@Id
	@Column(name = "order_id")
	private int orderId;
	@OneToOne
	private Customer customer;
	@OneToOne
	private Seller seller;
	@ManyToMany
	private List<Product> product;
	private LocalDate deliveryDate;
}

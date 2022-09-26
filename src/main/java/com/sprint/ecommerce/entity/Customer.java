package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {

	@Id
	@Column(name = "cust_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custId;

	@NonNull
	@Column(name = "cust_name")
	private String custName;

	@NonNull
	@Column(unique = true)
	private String userName;

	@NonNull
	private String password;

	@NonNull
	private String address;

	private List<Product> wishlist;

	private List<Orders> custOrders;

}

package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(scope = Customer.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "custId")

public class Customer {

	@Id
	@Column(name = "cust_id")
	private int custId;

	@NonNull
	@Column(name = "cust_name")
	private String custName;

	@NonNull
	@Column(unique = true)
	private String userName;

	@NonNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@NonNull
	private String address;

	@ManyToMany
	private List<Product> wishlist;

	@OneToMany
	private List<Orders> custOrders;

	public void addToWishlist(Product p) {
		wishlist.add(p);
	}

	public void addToCustOrders(Orders o) {
		custOrders.add(o);
	}

}

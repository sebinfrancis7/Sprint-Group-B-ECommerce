package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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

	@ManyToMany
	private List<Product> wishlist;
	
	@OneToMany
	private List<Orders> custOrders;
	
	public void addToWishlist(Product p){
		wishlist.add(p);
	}

	public void addToCustOrders(Orders o) {
		custOrders.add(o);
	}

}

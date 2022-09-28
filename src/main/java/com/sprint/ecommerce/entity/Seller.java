package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private double rating;

	@OneToMany
	private List<Product> product;

}

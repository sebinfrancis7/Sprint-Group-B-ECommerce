package com.sprint.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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

	@NonNull
	@Column(name = "seller_name")
	private String sellerName;
	
	@NonNull
	@Column(unique = true)
	private String userName;
	
	@NonNull
	@JsonProperty(access = Access.WRITE_ONLY)
	@Pattern(regexp = "^[a-zA-Z0-9]{4,}", message = "Password must contain atleast 4 letters and no special characters.")
	private String password;
	
	@NonNull
	@Min(value = 1)
	@Max(value = 5)
	private double rating;

	@OneToMany
	private List<Product> product;

}

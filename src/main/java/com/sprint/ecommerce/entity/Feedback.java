package com.sprint.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

	@Id
	private int feedbackId;
	@OneToOne
	private Customer customer;
	@OneToOne
	private Seller seller;
	@OneToOne
	private Product product;
	private int rating;
	private String feedback;
}

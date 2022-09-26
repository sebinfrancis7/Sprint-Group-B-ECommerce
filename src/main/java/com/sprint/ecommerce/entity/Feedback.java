package com.sprint.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

	private int feedbackId;
	private Customer customer;
	private Seller seller;
	private Product product;
	private int rating;
	private String feedback;
}

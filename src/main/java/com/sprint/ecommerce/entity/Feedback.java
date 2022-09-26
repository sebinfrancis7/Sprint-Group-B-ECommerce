package com.sprint.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "FEEDBACK_DETAILS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private int feedbackId;
	@NonNull
	private Customer customer;
	@NonNull
	private Seller seller;
	@NonNull
	private Product product;
	@NonNull
	private int rating;
	@NonNull
	private String feedback;
}

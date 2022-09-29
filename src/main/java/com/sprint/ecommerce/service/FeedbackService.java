package com.sprint.ecommerce.service;

import java.util.List;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.FeedbackResponse;
import com.sprint.ecommerce.exception.NotFoundException;

public interface FeedbackService {

	Feedback addFeedback(Feedback feedback);

	List<Feedback> getAllFeedbacks();

	Feedback getFeedbackById(int id) throws NotFoundException;

	Feedback updateFeedbackById(int id, Feedback feedback) throws NotFoundException;

	String deleteFeedbackById(int id) throws NotFoundException;

	void updateProductRating(Feedback feedback);

	void updateSellerRating(int id, double rating);

	Feedback getFeedbackByOrderId(int id) throws NotFoundException;

	List<FeedbackResponse> getFeedbackByCustomerId(int id) throws NotFoundException;

	List<FeedbackResponse> getFeedbackByProductId(int id) throws NotFoundException;

}
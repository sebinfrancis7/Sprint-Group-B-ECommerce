package com.sprint.ecommerce.service;

import java.util.List;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.helpers.FeedbackResponse;

public interface FeedbackService {

	Feedback addFeedback(Feedback feedback) throws NotFoundException;

	List<Feedback> getAllFeedbacks() throws NotFoundException;

	Feedback getFeedbackById(int id) throws NotFoundException;

	Feedback updateFeedbackById(int id, Feedback feedback) throws NotFoundException;

	String deleteFeedbackById(int id) throws NotFoundException;

	void updateProductRating(Feedback feedback);

	void updateSellerRating(int id);

	Feedback getFeedbackByOrderId(int id) throws NotFoundException;

	List<FeedbackResponse> getFeedbackByCustomerId(int id) throws NotFoundException;

	List<FeedbackResponse> getFeedbackByProductId(int id) throws NotFoundException;

}
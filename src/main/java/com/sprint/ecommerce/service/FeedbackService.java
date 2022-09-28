package com.sprint.ecommerce.service;

import java.util.List;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.exception.NotFoundException;

public interface FeedbackService {

	Feedback addFeedback(Feedback feedback);

	List<Feedback> getAllFeedbacks();

	Feedback updateFeedbackById(int id, Feedback feedback) throws NotFoundException;

	String deleteFeedbackById(int id) throws NotFoundException;

	void updateRating(Feedback feedback);

}
package com.sprint.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.FeedbackResponse;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackServ;

	@GetMapping("")
	public ResponseEntity<List<Feedback>> getAllFeedbacks() throws NotFoundException {
		List<Feedback> list = feedbackServ.getAllFeedbacks();
		return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id) throws NotFoundException {
		Feedback response = feedbackServ.getFeedbackById(id);
		return new ResponseEntity<Feedback>(response, HttpStatus.OK);
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<Feedback> getFeedbackByOrderId(@PathVariable int id) throws NotFoundException {
		Feedback response = feedbackServ.getFeedbackByOrderId(id);
		return new ResponseEntity<Feedback>(response, HttpStatus.OK);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<List<FeedbackResponse>> getFeedbackByCustomerId(@PathVariable int id)
			throws NotFoundException {
		List<FeedbackResponse> response = feedbackServ.getFeedbackByCustomerId(id);
		return new ResponseEntity<List<FeedbackResponse>>(response, HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<List<FeedbackResponse>> getFeedbackByProductId(@PathVariable int id)
			throws NotFoundException {
		List<FeedbackResponse> response = feedbackServ.getFeedbackByProductId(id);
		return new ResponseEntity<List<FeedbackResponse>>(response, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Feedback> saveFeedback(@Valid @RequestBody Feedback feedback) throws NotFoundException {
		Feedback response = feedbackServ.addFeedback(feedback);
		return new ResponseEntity<Feedback>(response, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Feedback> updateFeedbackById(@PathVariable int id, @Valid @RequestBody Feedback feedback)
			throws NotFoundException {
		Feedback response = feedbackServ.updateFeedbackById(id, feedback);
		return new ResponseEntity<Feedback>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFeedbackById(@PathVariable int id) throws NotFoundException {
		String response = feedbackServ.deleteFeedbackById(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}

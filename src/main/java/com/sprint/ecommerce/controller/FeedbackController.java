package com.sprint.ecommerce.controller;

import java.util.List;

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
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackServ;

	@GetMapping("")
	public ResponseEntity<List<Feedback>> getAllFeedbacks() {
		List<Feedback> list = feedbackServ.getAllFeedbacks();
		return new ResponseEntity<List<Feedback>>(list, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
		Feedback response = feedbackServ.addFeedback(feedback);
		return new ResponseEntity<Feedback>(response, HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Feedback> updateFeedbackById(@PathVariable int id, @RequestBody Feedback feedback)
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

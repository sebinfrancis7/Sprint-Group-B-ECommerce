package com.sprint.ecommerce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.repository.CustomerRepository;
import com.sprint.ecommerce.repository.FeedbackRepository;
import com.sprint.ecommerce.repository.OrdersRepository;
import com.sprint.ecommerce.repository.ProductRepository;
import com.sprint.ecommerce.repository.SellerRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private OrdersRepository ordersRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private SellerRepository sellerRepo;

	@Override
	public Feedback addFeedback(Feedback feedback) {
		LocalDate date = LocalDate.now();
		feedback.setDateCreated(date);
		Orders o = ordersRepo.findById(feedback.getOrder().getOrderId()).get();
		feedback.setOrder(o);
		Feedback response = feedbackRepo.save(feedback);
//		double rating = productRepo.findProductRating(feedback.getOrder().getProduct().getProdId());
//		int count = productRepo.findProductCount(feedback.getOrder().getProduct().getProdId());
//		System.out.println("rc" + rating + " " + count);
//		double feedbackRating = feedback.getRating();
//		double newfbRating = (feedbackRating + rating) / (count + 1);
//		Product p1 = productRepo.findById(feedback.getOrder().getProduct().getProdId()).get();
//		p1.setRating(newfbRating);
//		productRepo.save(p1);
		// set rating
		// find sum of rating of all product with given id by custom query
		// find count of product by custom query
		// rating = sum/count;
//		int productID = feedback.getOrder().getProduct().getProdId();
//		Product p = productRepo.findById(productID).get();
//		
		return response;
	}

	@Override
	public List<Feedback> getAllFeedbacks() {
		return feedbackRepo.findAll();
	}

	@Override
	public String deleteFeedbackById(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (feedbackRepo.findById(id).isEmpty())
			throw new NotFoundException();
		feedbackRepo.deleteById(id);
		return "Feedback deleted successfully";
	}

	@Override
	public Feedback updateFeedbackById(int id, Feedback feedback) throws NotFoundException {
		// TODO Auto-generated method stub
		if (feedbackRepo.findById(id).isEmpty())
			throw new NotFoundException();
		Feedback f = feedbackRepo.findById(id).get();
		f.setRating(feedback.getRating());
		f.setFeedback(feedback.getFeedback());
		return feedbackRepo.save(f);
	}
}

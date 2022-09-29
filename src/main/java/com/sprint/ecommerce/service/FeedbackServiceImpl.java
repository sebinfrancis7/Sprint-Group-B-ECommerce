package com.sprint.ecommerce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.FeedbackResponse;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
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
		// save feedback
		LocalDate date = LocalDate.now();
		feedback.setDateCreated(date);
		Orders o = ordersRepo.findById(feedback.getOrder().getOrderId()).get();
		feedback.setOrder(o);
		Feedback response = feedbackRepo.save(feedback);

		// update product rating
		updateProductRating(feedback);

		// update seller rating
		int sellerId = o.getSeller().getSellerId();
		updateSellerRating(sellerId);

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

		Feedback feedback = feedbackRepo.findById(id).get();
		feedbackRepo.deleteById(id);
		updateProductRating(feedback);

		int sellerId = feedback.getOrder().getSeller().getSellerId();
		updateSellerRating(sellerId);
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
		Feedback response = feedbackRepo.save(f);
		updateProductRating(f);

		int sellerID = f.getOrder().getSeller().getSellerId();
		updateSellerRating(sellerID);
		return response;
	}

	@Override
	public void updateProductRating(Feedback feedback) {
		// TODO Auto-generated method stub
		double totalRating = feedbackRepo.findTotalRatingOfProduct(feedback.getOrder().getProduct());
		int totalCount = feedbackRepo.findCountOfProductFeedback(feedback.getOrder().getProduct());
		double newRating = totalCount == 0 ? 0 : ((totalRating) / (totalCount));
		newRating = Math.round(newRating * 100.0) / 100.0;
		System.out.println(totalCount + " " + totalRating + " " + newRating);
		Product p1 = productRepo.findById(feedback.getOrder().getProduct().getProdId()).get();
		p1.setRating(newRating);
		productRepo.save(p1);

	}

	@Override
	public void updateSellerRating(int id) {
		// TODO Auto-generated method stub
		Seller s = sellerRepo.findById(id).get();
		List<Product> list = s.getProduct();
		double sellerRating = s.getRating();
		int totalProductCount = list.size();
		double totalProductRating = list.stream().map(p -> p.getRating()).mapToDouble(Double::doubleValue).sum();

		double updatedSellerRating = totalProductRating / totalProductCount;
		updatedSellerRating = Math.round(updatedSellerRating * 100.0) / 100.0;

		s.setRating(updatedSellerRating);
		sellerRepo.save(s);
	}

	@Override
	public Feedback getFeedbackByOrderId(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!ordersRepo.existsById(id))
			throw new NotFoundException("order not found");
		return feedbackRepo.getFeedbackFromOrderID(id);
	}

	@Override
	public List<FeedbackResponse> getFeedbackByCustomerId(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!customerRepo.existsById(id))
			throw new NotFoundException("order not found");
		return feedbackRepo.getFeedbackFromCustomerID(id);
	}

	@Override
	public List<FeedbackResponse> getFeedbackByProductId(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!productRepo.existsById(id))
			throw new NotFoundException("order not found");
		return feedbackRepo.getFeedbackFromProductID(id);

	}

	@Override
	public Feedback getFeedbackById(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!feedbackRepo.existsById(id))
			throw new NotFoundException();
		return feedbackRepo.findById(id).get();
	}
}

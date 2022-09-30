package com.sprint.ecommerce.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.helpers.FeedbackResponse;
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
	public Feedback addFeedback(Feedback feedback) throws NotFoundException {
		// save feedback
		LocalDate date = LocalDate.now();
		feedback.setDateCreated(date);
		if (!ordersRepo.existsById(feedback.getOrder().getOrderId())) {
			throw new NotFoundException("Order with given ID does not exists");
		}
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
	public List<Feedback> getAllFeedbacks() throws NotFoundException {
		List<Feedback> list = feedbackRepo.findAll();
		if (list.isEmpty())
			throw new NotFoundException("No feedbacks have been added. Check again later.");

		return list;
	}

	@Override
	public String deleteFeedbackById(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (feedbackRepo.findById(id).isEmpty())
			throw new NotFoundException("Feedback with given ID does not exists!");

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
			throw new NotFoundException("Feedback with given ID : " + id + " does not exists");
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
			throw new NotFoundException("Order with given ID does not exists!");
		Feedback response = feedbackRepo.getFeedbackFromOrderID(id);
		if (response == null) {
			throw new NotFoundException("Customer has not given any feedback for this order yet. Check again later.");
		}
		return response;
	}

	@Override
	public List<FeedbackResponse> getFeedbackByCustomerId(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!customerRepo.existsById(id))
			throw new NotFoundException("Customer with given ID does not exists!");
		List<FeedbackResponse> list = feedbackRepo.getFeedbackFromCustomerID(id);
		if (list.isEmpty()) {
			throw new NotFoundException(
					"Customer with id : " + id + " has not given any feedback yet. Check again later.");
		}
		return list;
	}

	@Override
	public List<FeedbackResponse> getFeedbackByProductId(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!productRepo.existsById(id))
			throw new NotFoundException("Product with given ID does not exists!");
		List<FeedbackResponse> list = feedbackRepo.getFeedbackFromProductID(id);
		if (list.isEmpty()) {
			throw new NotFoundException(
					"Product with id :" + id + " does not have any feedback yet. Check again later.");
		}
		return list;

	}

	@Override
	public Feedback getFeedbackById(int id) throws NotFoundException {
		// TODO Auto-generated method stub
		if (!feedbackRepo.existsById(id))
			throw new NotFoundException("Feedback with given ID does not exists!");
		return feedbackRepo.findById(id).get();
	}
}

package com.sprint.ecommerce;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.ecommerce.entity.Feedback;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.service.CustomerService;
import com.sprint.ecommerce.service.FeedbackService;
import com.sprint.ecommerce.service.OrdersService;
import com.sprint.ecommerce.service.ProductService;
import com.sprint.ecommerce.service.SellerService;

@SpringBootTest
public class FeedbackTest {
	@Autowired
	ProductService prodServ;

	@Autowired
	SellerService sellerServ;

	@Autowired
	CustomerService custServ;

	@Autowired
	OrdersService orderServ;

	@Autowired
	FeedbackService feedbackServ;

	@Test
	@Transactional
	@Rollback(false)
	public void addFeedback() throws NotFoundException {
		Orders o = new Orders();
		o.setOrderId(1000);

		Feedback f = new Feedback();
		f.setOrder(o);
		f.setRating(4);
		f.setFeedback("very good product");

		Feedback savedFeedback = feedbackServ.addFeedback(f);
		assertNotNull(savedFeedback);
	}
}

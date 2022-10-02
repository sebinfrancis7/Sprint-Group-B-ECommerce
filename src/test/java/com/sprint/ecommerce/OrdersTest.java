package com.sprint.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.service.CustomerService;
import com.sprint.ecommerce.service.FeedbackService;
import com.sprint.ecommerce.service.OrdersService;
import com.sprint.ecommerce.service.ProductService;
import com.sprint.ecommerce.service.SellerService;

@SpringBootTest
public class OrdersTest {

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
	public void placeOrderTest() throws Exception {

		Orders o = new Orders();
		o.setOrderId(1000);

		Seller s = new Seller();
		s.setSellerId(1000);
		Product p = new Product();
		p.setProdId(1001);

		o.setSeller(s);
		o.setProduct(p);

		String resp = custServ.placeOrder(1000, o);
		assertEquals("Order placed successfully", resp);
	}
}

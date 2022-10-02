package com.sprint.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.repository.OrdersRepository;
import com.sprint.ecommerce.service.CustomerService;
import com.sprint.ecommerce.service.FeedbackService;
import com.sprint.ecommerce.service.OrdersService;
import com.sprint.ecommerce.service.ProductService;
import com.sprint.ecommerce.service.SellerService;

@SpringBootTest
class EcommerceApplicationTests {

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

	@Autowired
	OrdersRepository ordersRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveProductTest() {
		Product p1 = new Product();
		p1.setProdId(1000);
		p1.setProdName("iphone");
		p1.setCategory("Electronics");
		p1.setPrice(165000);
		try {
			prodServ.saveProduct(p1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Product savedProduct1 = null;
		try {
			savedProduct1 = prodServ.getProductById(1000).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(p1, savedProduct1);
	}

	@Test
	public void saveSellerTest() throws Exception {
		Seller s = new Seller();
		s.setSellerId(1000);
		s.setSellerName("Rohit Sharma");
		s.setUserName("r_203");
		s.setPassword("password");

		sellerServ.saveSeller(s);

		Seller savedSeller = sellerServ.getSellerById(1000).get();

		System.out.println(s.getSellerId());
		System.out.println(savedSeller.getSellerId());
		assertEquals(s, savedSeller);
	}

	@Transactional
	@Rollback(false)
	@Test
	public void addProductToSellerTest() throws NotFoundException, AlreadyExistsException, Exception {
		Product p2 = new Product();
		p2.setProdId(1001);
		p2.setProdName("shoes");
		p2.setCategory("Apparel");
		p2.setPrice(5438);

		String resp = sellerServ.addToProductList(1000, p2);
		assertEquals("Product added successfully", resp);
	}

	@Test
	public void saveCustomerTest() throws Exception {
		Customer c = new Customer();
		c.setCustId(1000);
		c.setCustName("rahul mehta");
		c.setUserName("rm07");
		c.setPassword("password");

		custServ.addCustomer(c);

		Customer savedCustomer = custServ.getCustomerById(1000).get();

		assertEquals(c.getCustId(), savedCustomer.getCustId());
	}

//	@Test
//	@Transactional
//	@Rollback(false)
//	public void placeOrderTest() throws Exception {
//
//		Orders o = new Orders();
//		o.setOrderId(1000);
//
//		Seller s = new Seller();
//		s.setSellerId(1000);
//		Product p = new Product();
//		p.setProdId(1001);
//		Customer c = new Customer();
//		c.setCustId(1000);
//		o.setSeller(s);
//		o.setProduct(p);
//		o.setCustomer(c);
//		o.setOrderDate(LocalDateTime.now());
//		o.setDeliveryDate(LocalDate.now().plusDays(3));
//		ordersRepo.saveAndFlush(o);
//
//	}
//
//	@Test
//	@Transactional
//	public void findPlacedOrder() throws NotFoundException {
//		Orders savedOrder = orderServ.getOrderById(1000);
//		assertEquals(1000, savedOrder.getOrderId());
//	}

}

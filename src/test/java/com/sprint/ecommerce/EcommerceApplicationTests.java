package com.sprint.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.repository.ProductRepository;
import com.sprint.ecommerce.repository.SellerRepository;

@SpringBootTest
class EcommerceApplicationTests {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	SellerRepository sellerRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void saveProductTest() {
		Product p1 = new Product();
		p1.setProdId(107);
		p1.setProdName("iphone");
		p1.setCategory("Electronics");
		p1.setPrice(165000);
		productRepo.save(p1);

		Product savedProduct1 = productRepo.findById(105).get();
		assertEquals(p1, savedProduct1);
//
//		Product p2 = new Product();
//		p2.setProdId(106);
//		p2.setProdName("shoes");
//		p2.setCategory("Apparel");
//		p2.setPrice(5438);
//		productRepo.save(p2);
//
//		Product savedProduct2 = productRepo.findById(106).get();
//		assertEquals(p2, savedProduct2);
	}

	@Test
	public void saveSellerTest() {
		Seller s = new Seller();
		s.setSellerId(102);
		s.setSellerName("Rohit Sharma");
		s.setUserName("r_20asasd3");
		s.setPassword("password");
		Product p1 = productRepo.findById(105).get();
//		Product p2 = productRepo.findById(106).get();
//		List<Product> list = List.of(p1, p2);
		List<Product> list = List.of(p1);
		s.setProduct(list);

		sellerRepo.save(s);

		Seller savedSeller = sellerRepo.findById(102).get();

		System.out.println(s.getSellerId());
		System.out.println(savedSeller.getSellerId());
		assertEquals(s, savedSeller);
	}

}

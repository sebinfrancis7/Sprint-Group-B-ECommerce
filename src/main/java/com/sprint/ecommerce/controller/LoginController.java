package com.sprint.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.MismatchException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.service.CustomerService;
import com.sprint.ecommerce.service.SellerService;

@RestController
public class LoginController {

	@Autowired
	private CustomerService customerServ;

	@Autowired
	private SellerService sellerServ;

	@PostMapping("/login/customer")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer customer) throws NotFoundException, MismatchException {
		String response = customerServ.loginCustomer(customer);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@PostMapping("/login/seller")
	public ResponseEntity<String> loginSeller(@RequestBody Seller seller) throws NotFoundException {
		String response = sellerServ.loginSeller(seller);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}

package com.sprint.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService custServ;

	@GetMapping("customer")
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> list = custServ.getCustomers();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}

	@PostMapping("/save/customer")
	private ResponseEntity<String> saveCustomer(@RequestBody Customer c) throws AlreadyExistsException {
		Customer c1 = custServ.addCustomer(c);
		return new ResponseEntity<String>("Customer added successfully.", HttpStatus.CREATED);
	}

}

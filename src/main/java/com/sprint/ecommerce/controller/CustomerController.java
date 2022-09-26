package com.sprint.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService cServ;
	
	@PostMapping
	private ResponseEntity<String> saveCustomer(@RequestBody Customer c) throws AlreadyExistsException {
		Customer c1 = cServ.addCustomer(c);
		return new ResponseEntity<String>("Customer added successfully.", HttpStatus.CREATED);
	}

}

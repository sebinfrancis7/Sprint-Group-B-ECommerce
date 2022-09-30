package com.sprint.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.MismatchException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.exception.UniqueValueException;
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
	private ResponseEntity<String> saveCustomer(@Valid @RequestBody Customer c) throws AlreadyExistsException, UniqueValueException {
		Customer c1 = custServ.addCustomer(c);
		return new ResponseEntity<String>("Customer added successfully.", HttpStatus.CREATED);
	}

	@GetMapping("/customer/{custId}")
	private ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable("custId") int custId) throws NotFoundException {
		Optional<Customer> list = custServ.getCustomerById(custId);
		return new ResponseEntity<Optional<Customer>>(list, HttpStatus.OK);
	}

	@PostMapping("/delete/customer/{custId}")
	public ResponseEntity<Object> deleteById(@PathVariable("custId") int custId) throws NotFoundException {
		custServ.deleteCustomerById(custId);
		return new ResponseEntity<Object>("Customer deleted successfully", HttpStatus.OK);
	}

	@PatchMapping("/update/customer")
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer c) throws NotFoundException {
		custServ.updateCustomer(c.getCustId(), c);
		return new ResponseEntity<Object>("Customer updated successfully", HttpStatus.OK);
	}

	@PostMapping("/customer/{custId}/placeorder")
	public ResponseEntity<String> placeOrder(@PathVariable int custId, @RequestBody Orders o) throws AlreadyExistsException {
		String s = custServ.placeOrder(custId, o);
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}

	@PostMapping("/customer/{custId}/wishlist")
	public ResponseEntity<String> addWishlist(int custId, @RequestBody Product p) {
		String s = custServ.addWishlist(custId, p);
		return new ResponseEntity<String>(s, HttpStatus.OK);
	}
}

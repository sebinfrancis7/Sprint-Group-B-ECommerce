package com.sprint.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.service.OrdersService;

@RestController
public class OrdersController {
	@Autowired
	private OrdersService ordersServ;
	@PostMapping("/save/orders")
	public ResponseEntity<String> saveOrder(@RequestBody Orders orders) throws AlreadyExistsException{
		ordersServ.saveOrder(orders);
		return new ResponseEntity<String>("Order saved successfully",HttpStatus.ACCEPTED);
	}
	
	

}

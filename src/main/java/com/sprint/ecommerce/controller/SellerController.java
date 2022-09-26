package com.sprint.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.service.SellerService;

@RestController
public class SellerController {
	
	@Autowired
	private SellerService sellerServ;
	
	@PostMapping("/save/seller")
	public ResponseEntity<String> saveSeller(@RequestBody Seller seller) throws AlreadyExistsException{
		Seller s1 = sellerServ.saveSeller(seller);
		return new ResponseEntity<String>("Seller Saved Successfully",HttpStatus.OK);
	}
	
}

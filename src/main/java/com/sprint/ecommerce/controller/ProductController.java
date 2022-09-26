package com.sprint.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productServ;

	@PostMapping("/save/product")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) throws AlreadyExistsException {
		Product prod = productServ.saveProduct(product);
		return new ResponseEntity<String>("Product Saved Successfully", HttpStatus.OK);
	}

}

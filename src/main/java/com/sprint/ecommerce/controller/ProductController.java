package com.sprint.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService productServ;

	@PostMapping("/save/product")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) throws Exception {
		Product prod = productServ.saveProduct(product);
		return new ResponseEntity<String>("Product Saved Successfully", HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() throws Exception {
		List<Product> prod = productServ.getAllProducts();
		return new ResponseEntity<List<Product>>(prod, HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Optional<Product>> getProduct(@PathVariable int id) throws Exception {
		Optional<Product> prod = productServ.getProductById(id);
		if (prod == null) {
			return new ResponseEntity<Optional<Product>>(prod, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Optional<Product>>(prod, HttpStatus.OK);
		}

	}

	@PatchMapping("/update/product")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) throws Exception {
		productServ.updateProduct(product);
		return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
	}

	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) throws Exception {
		productServ.deleteProduct(id);
		return new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
	}

	@GetMapping("/product/category/{category}")
	public ResponseEntity<List<Product>> getProdByCategory(@PathVariable String category) throws Exception {
		List<Product> products = productServ.getProductsByCategory(category);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}

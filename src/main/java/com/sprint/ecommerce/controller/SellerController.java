package com.sprint.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.exception.UniqueValueException;
import com.sprint.ecommerce.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	private SellerService sellerServ;

	@PostMapping("/save/seller")
	public ResponseEntity<String> saveSeller(@Valid @RequestBody Seller seller)
			throws AlreadyExistsException, UniqueValueException {
		Seller s1 = sellerServ.saveSeller(seller);
		return new ResponseEntity<String>("Seller Saved Successfully", HttpStatus.OK);
	}

	@GetMapping("/sellers")
	public ResponseEntity<List<Seller>> getSellers() throws NotFoundException {
		List<Seller> list = sellerServ.getAllSellers();
		return new ResponseEntity<List<Seller>>(list, HttpStatus.OK);
	}

	@GetMapping("/seller/{sellerId}")
	public ResponseEntity<Optional<Seller>> getSellerById(@PathVariable int sellerId) throws NotFoundException {
		Optional<Seller> seller = sellerServ.getSellerById(sellerId);
		return new ResponseEntity<Optional<Seller>>(seller, HttpStatus.OK);
	}

	@DeleteMapping("/delete/seller/{sellerId}")
	public ResponseEntity<String> deleteSellerById(@PathVariable int sellerId) throws NotFoundException {
		sellerServ.deleteSeller(sellerId);
		return new ResponseEntity<String>("Seller Deleted Successfully", HttpStatus.ACCEPTED);
	}

	@PatchMapping("/update/seller")
	public ResponseEntity<String> update(@RequestBody Seller seller) throws NotFoundException {
		sellerServ.updateSeller(seller);
		return new ResponseEntity<String>("Seller Updated Successfully", HttpStatus.ACCEPTED);
	}

	@GetMapping("/seller/ratingAbove/{rating}")
	public ResponseEntity<List<Seller>> filterSellerAboveRating(@PathVariable double rating) throws NotFoundException {
		List<Seller> sellers = sellerServ.filterAboveRating(rating);
		return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
	}

	@PostMapping("/seller/{sellerId}/addproduct")
	public ResponseEntity<String> addToProductList(@PathVariable int sellerId, @RequestBody Product p)
			throws AlreadyExistsException, Exception {
		String resp = sellerServ.addToProductList(sellerId, p);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

	@DeleteMapping("/seller/{sellerId}/removeproduct/{prodId}")
	public ResponseEntity<String> removeFromProductList(@PathVariable int sellerId, @PathVariable int prodId)
			throws NotFoundException {
		String resp = sellerServ.removeFromProductList(sellerId, prodId);
		return new ResponseEntity<String>(resp, HttpStatus.OK);
	}

}

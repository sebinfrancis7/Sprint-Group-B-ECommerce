package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;

public interface SellerService {
	
	Seller saveSeller(Seller seller) throws AlreadyExistsException;
	
	List<Seller> getAllSellers();
	
	Optional<Seller> getSellerById(int sellerId) throws NotFoundException;
	
	Seller deleteSeller(int sellerId) throws NotFoundException;	
	
	Seller updateSeller(Seller seller) throws NotFoundException;

}

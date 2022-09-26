package com.sprint.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService{

	@Autowired
	private SellerRepository sellerRepo;
	
	@Override
	public Seller saveSeller(Seller seller) throws AlreadyExistsException {
		if(sellerRepo.existsById(seller.getSellerId())) {
			throw new AlreadyExistsException();
		}
		Seller s1 = sellerRepo.save(seller);
		return s1;
	}

}

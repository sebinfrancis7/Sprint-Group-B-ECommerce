package com.sprint.ecommerce.service;

import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;

public interface SellerService {
	
	Seller saveSeller(Seller seller) throws AlreadyExistsException;

}

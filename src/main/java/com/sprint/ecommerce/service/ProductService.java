package com.sprint.ecommerce.service;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;

public interface ProductService {
	Product saveProduct(Product product) throws AlreadyExistsException;
}

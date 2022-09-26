package com.sprint.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product saveProduct(Product product) throws AlreadyExistsException {
		if(productRepo.existsById(product.getProdId())) {
			throw new AlreadyExistsException();
		}
		Product p1 = productRepo.save(product);
		return p1;
	}

}

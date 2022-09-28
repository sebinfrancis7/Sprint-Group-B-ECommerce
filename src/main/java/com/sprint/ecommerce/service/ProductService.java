package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;

public interface ProductService {
	Product saveProduct(Product product) throws AlreadyExistsException, Exception;

	List<Product> getAllProducts() throws AlreadyExistsException, Exception;

	Optional<Product> getProductById(int id) throws NotFoundException, Exception;

	void updateProduct(Product p) throws NotFoundException, Exception;

	void deleteProduct(int id) throws NotFoundException, Exception;

	List<Product> getProductsByCategory(String category) throws NotFoundException;
}

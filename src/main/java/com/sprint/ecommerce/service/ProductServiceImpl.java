package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product saveProduct(Product product) throws AlreadyExistsException {
		if (productRepo.existsById(product.getProdId())) {
			throw new AlreadyExistsException();
		}
		Product p1 = productRepo.save(product);
		return p1;
	}

	@Override
	public Optional<Product> getProductById(int id) throws NotFoundException, Exception {
		Optional<Product> product1 = productRepo.findById(id);
		if (product1 == null) {
			throw new NotFoundException();
		}
		return product1;
	}

	@Override
	public List<Product> getAllProducts() throws AlreadyExistsException, Exception {
		List<Product> productList = productRepo.findAll();
		if (productList.size() < 1) {
			throw new NotFoundException();
		}
		return productList;
	}

	@Override
	public void updateProduct(Product product) throws NotFoundException, Exception {
		if (productRepo.existsById(product.getProdId())) {
			productRepo.save(product);
		} else {
			throw new NotFoundException();
		}

	}

	@Override
	public void deleteProduct(int id) throws NotFoundException, Exception {
		if (productRepo.existsById(id)) {
			productRepo.deleteById(id);
		} else {
			throw new NotFoundException();
		}

	}

	@Override
	public List<Product> getProductsByCategory(String category) throws NotFoundException {
		List<Product> list = productRepo.findProductsByCategory(category);
		if (list.size() < 1) {
			throw new NotFoundException();
		}
		return list;
	}

}

package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;

@Service
public interface CustomerService {

	Customer addCustomer(Customer c) throws AlreadyExistsException;

	List<Customer> getCustomers();

	void deleteCustomerById(int custId) throws NotFoundException;

	void updateCustomer(int custId, Customer c) throws NotFoundException;

	Optional<Customer> getCustomerById(int custId);

	String loginCustomer(Customer customer) throws NotFoundException;

	String placeOrder(int custId, Orders o);

	String addWishlist(int custId, Product p);


}

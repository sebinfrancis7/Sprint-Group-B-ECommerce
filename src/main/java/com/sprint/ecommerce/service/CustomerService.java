package com.sprint.ecommerce.service;

import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.exception.AlreadyExistsException;

@Service
public interface CustomerService {

	Customer addCustomer(Customer c) throws AlreadyExistsException;

}

package com.sprint.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cRepo;

	@Override
	public Customer addCustomer(Customer c) throws AlreadyExistsException {
		if (cRepo.existsById(c.getCustId()))
			throw new AlreadyExistsException();
		Customer c1 = cRepo.save(c);
		return c1;
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return cRepo.findAll();
	}

}

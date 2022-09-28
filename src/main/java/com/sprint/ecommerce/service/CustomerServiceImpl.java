package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
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

	@Override
	public Optional<Customer> getCustomerById(int custId) {
		return cRepo.findById(custId);
	}

	@Override
	public void deleteCustomerById(int custId) throws NotFoundException {
		if (!cRepo.existsById(custId))
			throw new NotFoundException();
		cRepo.deleteById(custId);
		;
	}

	@Override
	public void updateCustomer(int custId, Customer c) throws NotFoundException {
		if (!cRepo.existsById(custId)) {
			throw new NotFoundException();
		}
		Customer c1 = cRepo.getById(custId);
		if (!c.getCustName().isEmpty())
			c1.setCustName(c.getCustName());
		if (!c.getUserName().isEmpty())
			c1.setUserName(c.getUserName());
		if (!c.getPassword().isEmpty())
			c1.setPassword(c.getPassword());
		if (!c.getAddress().isEmpty())
			c1.setAddress(c.getAddress());
		if (!c.getWishlist().isEmpty())
			c1.setWishlist(c.getWishlist());
		if (c.getCustOrders().isEmpty())
			c1.setCustOrders(c.getCustOrders());

		cRepo.save(c1);
	}

	@Override
	public String loginCustomer(Customer customer) throws NotFoundException {
		if(cRepo.existsById(customer.getCustId())) {
			Customer c1  = cRepo.findById(customer.getCustId()).get();
			if(customer.getUserName().equals(c1.getUserName()) && customer.getPassword().equals(c1.getPassword())) {
				return "Customer logged in";
			} else {
				return "Invalid Credentials";
			}
		} else {
			throw new NotFoundException();
		}
	}

}

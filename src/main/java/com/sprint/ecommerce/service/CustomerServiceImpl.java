package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.repository.CustomerRepository;
import com.sprint.ecommerce.repository.OrdersRepository;
import com.sprint.ecommerce.repository.ProductRepository;
import com.sprint.ecommerce.repository.SellerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository cRepo;
	
	@Autowired
	private ProductRepository pRepo;
	
	@Autowired
	private SellerRepository sRepo;
	
	@Autowired
	private OrdersRepository oRepo;

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
//		if (!c.getWishlist().isEmpty())
//			c1.setWishlist(c.getWishlist());
//		if (c.getCustOrders().isEmpty())
//			c1.setCustOrders(c.getCustOrders());
//
//		if(!c.getCustOrders().isEmpty()) {
//			c1.setCustOrders(c.getCustOrders());
//		}
			
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
			
	@Override
	public String placeOrder(int custId, Orders o) {

		if(cRepo.existsById(custId))
		{
			Customer c = cRepo.findById(custId).get();
			Orders o1 = new Orders(o.getOrderId(),o.getCustomer(),o.getSeller(),o.getProduct(),o.getDeliveryDate());
			oRepo.save(o1);
			c.addToCustOrders(o1);
			cRepo.save(c);
			return "Order placed successfully";
		}
		else {
			return "Customer with given id does not exist";
		}
	}

	@Override
	public String addWishlist(int custId, Product p) {
		
//		Customer c = cRepo.getById(custId);
//		List<Product> p1 =c.getWishlist();
//		p1.addAll(p);
//		c.setWishlist(p1);
//		try {
//			this.updateCustomer(custId, c);
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		if(cRepo.existsById(custId))
		{
			Customer c = cRepo.findById(custId).get();
			Product p1 = pRepo.findById(p.getProdId()).get();
			c.addToWishlist(p1);
			cRepo.save(c);
			return "wishlist updated successfully";
		}
		else {
			return "Customer with given id does not exist";
		}
	}

}

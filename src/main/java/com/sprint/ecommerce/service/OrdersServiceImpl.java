package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.repository.CustomerRepository;
import com.sprint.ecommerce.repository.OrdersRepository;
import com.sprint.ecommerce.repository.SellerRepository;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private SellerRepository sellerRepo;
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public Orders saveOrder(Orders orders) throws AlreadyExistsException {
		if (ordersRepo.existsById(orders.getOrderId()))
			throw new AlreadyExistsException();
		return ordersRepo.save(orders);
	}

	@Override
	public List<Orders> getAllOrders() {
		return ordersRepo.findAll();
	}

	@Override
	public Orders getOrderById(int id) throws NotFoundException {
		Optional<Orders> findById = ordersRepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else
			throw new NotFoundException();
	}

	@Override
	public void update(Orders orders) throws NotFoundException {
		Orders order1 = ordersRepo.findById(orders.getOrderId()).get();
		if (order1 == null)
			throw new NotFoundException();
		ordersRepo.save(orders);
	}

	@Override
	public void delete(int id) throws NotFoundException {
		if (ordersRepo.findById(id).get() == null)
			throw new NotFoundException();
		ordersRepo.delete(ordersRepo.findById(id).get());

	}

	@Override
	public List<Orders> getOrdersBySeller(int id) throws NotFoundException {
		if(sellerRepo.existsById(id)) {
		Seller s1 = sellerRepo.findById(id).get();
		List<Orders> findBySeller = ordersRepo.findBySeller(s1);
		return findBySeller;
		}
		else {
			throw new NotFoundException();
		}
	}

	@Override
	public List<Orders> getOrdersByCustomer(int id) throws NotFoundException {
		if (customerRepo.existsById(id)) {
			Customer s1 = customerRepo.findById(id).get();
			List<Orders> findByCustomer = ordersRepo.findByCustomer(s1);
			return findByCustomer;
		} else {
			throw new NotFoundException();
		}
	}

}

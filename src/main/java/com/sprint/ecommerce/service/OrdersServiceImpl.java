package com.sprint.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.repository.OrdersRepository;
@Service
public class OrdersServiceImpl implements OrdersService{
	@Autowired
	private OrdersRepository ordersRepo;
	@Override
	public Orders saveOrder(Orders orders) throws AlreadyExistsException {
		if(ordersRepo.existsById(orders.getOrderId()))
			throw new AlreadyExistsException();
		return ordersRepo.save(orders);
	}

}

package com.sprint.ecommerce.service;

import java.util.List;

import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;

public interface OrdersService {
	Orders saveOrder(Orders orders) throws AlreadyExistsException;

	List<Orders> getAllOrders();

	Orders getOrderById(int id) throws NotFoundException;

	void update(Orders orders) throws NotFoundException;

	void delete(int id) throws NotFoundException;

	List<Orders> getOrdersBySeller(int id) throws NotFoundException;
	List<Orders> getOrdersByCustomer(int id) throws NotFoundException;

}

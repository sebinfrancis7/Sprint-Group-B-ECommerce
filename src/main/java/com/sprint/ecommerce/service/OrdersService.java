package com.sprint.ecommerce.service;

import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.AlreadyExistsException;

public interface OrdersService {
	Orders saveOrder(Orders orders) throws AlreadyExistsException;

}

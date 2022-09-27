package com.sprint.ecommerce.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
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
	@Override
	public List<Orders> getAllOrders() {
		return ordersRepo.findAll();
	}
	@Override
	public Orders getOrderById(int id) throws NotFoundException {
		Optional<Orders> findById = ordersRepo.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}
		else throw new NotFoundException();
	}
	@Override
	public void update(Orders orders) throws NotFoundException {
		Orders order1 = ordersRepo.findById(orders.getOrderId()).get();
		if(order1==null) throw new NotFoundException();
		ordersRepo.delete(order1);
		ordersRepo.save(orders);	
	}
	@Override
	public void delete(int id) throws NotFoundException {
		if(ordersRepo.findById(id).get()==null) throw new NotFoundException();
		ordersRepo.delete(ordersRepo.findById(id).get());
		
	}

}

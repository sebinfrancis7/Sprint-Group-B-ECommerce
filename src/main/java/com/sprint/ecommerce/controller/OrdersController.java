package com.sprint.ecommerce.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.service.OrdersService;

@RestController
public class OrdersController {
	@Autowired
	private OrdersService ordersServ;
	@PostMapping("/save/order")
	public ResponseEntity<String> saveOrder(@RequestBody Orders orders) throws AlreadyExistsException{
		ordersServ.saveOrder(orders);
		return new ResponseEntity<String>("Order saved successfully",HttpStatus.ACCEPTED);
	}
	@GetMapping("/order")
	public ResponseEntity<List<Orders>> getAllOrders(){
		List<Orders> allOrders = ordersServ.getAllOrders();
		return new ResponseEntity<List<Orders>>(allOrders,HttpStatus.OK);
	}
	@GetMapping("/orders/{id}")
	public ResponseEntity<Orders> getOrderById(@PathVariable int id) throws NotFoundException{
		Orders orderById = ordersServ.getOrderById(id);
		return new ResponseEntity<Orders>(orderById,HttpStatus.OK);
		
	}
	@PostMapping("/update/order")
	public ResponseEntity<String> update(@RequestBody Orders orders) throws NotFoundException {
		ordersServ.update(orders);
		return new ResponseEntity<String>("Order Updated Successfully",HttpStatus.ACCEPTED);
		
	}
	@GetMapping("/delete/order/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) throws NotFoundException{
		ordersServ.delete(id);
		return new ResponseEntity<String>("Deleted successfully",HttpStatus.ACCEPTED);
	}
	

}

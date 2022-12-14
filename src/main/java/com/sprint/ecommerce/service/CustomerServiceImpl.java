package com.sprint.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.ecommerce.entity.Customer;
import com.sprint.ecommerce.entity.Orders;
import com.sprint.ecommerce.entity.Product;
import com.sprint.ecommerce.entity.Seller;
import com.sprint.ecommerce.exception.AlreadyExistsException;
import com.sprint.ecommerce.exception.MismatchException;
import com.sprint.ecommerce.exception.NotFoundException;
import com.sprint.ecommerce.exception.UniqueValueException;
import com.sprint.ecommerce.helpers.PasswordHash;
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
	@Autowired
	private OrdersService ordersServ;

	@Override
	public Customer addCustomer(Customer c) throws AlreadyExistsException, UniqueValueException {
		if (cRepo.existsById(c.getCustId()))
			throw new AlreadyExistsException("Customer Already exists");
		List<String> userNameList = cRepo.uniqueUserName();
		if (userNameList.contains(c.getUserName())) {
			throw new UniqueValueException("Username already exists");
		}
		PasswordHash p1 = new PasswordHash();
		c.setPassword(p1.encrypt(c.getPassword()));
		Customer c1 = cRepo.save(c);
		return c1;
	}

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return cRepo.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int custId) throws NotFoundException {
		if (!cRepo.existsById(custId))
			throw new NotFoundException("Customer not found");
		return cRepo.findById(custId);
	}

	@Override
	public void deleteCustomerById(int custId) throws NotFoundException {
		if (!cRepo.existsById(custId))
			throw new NotFoundException("Customer not found");
		cRepo.deleteById(custId);
		;
	}

	@Override
	public void updateCustomer(int custId, Customer c) throws NotFoundException {
		if (!cRepo.existsById(custId)) {
			throw new NotFoundException("Customer not found");
		}
		PasswordHash p1 = new PasswordHash();
		Customer c1 = cRepo.getById(custId);
		if (!c.getCustName().isEmpty())
			c1.setCustName(c.getCustName());
		if (!c.getUserName().isEmpty())
			c1.setUserName(c.getUserName());
		if (!c.getPassword().isEmpty())
			c1.setPassword(p1.encrypt(c.getPassword()));
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
	public String loginCustomer(Customer customer) throws NotFoundException, MismatchException {
		PasswordHash p1 = new PasswordHash();
		if (cRepo.existsById(customer.getCustId())) {
			Customer c1 = cRepo.findById(customer.getCustId()).get();
			if (customer.getUserName().equals(c1.getUserName())
					&& customer.getPassword().equals(p1.decrypt(c1.getPassword()))) {
				return "Customer logged in";
			} else {
				throw new MismatchException("Incorrect Username or Password. Please try again.");
			}
		} else {
			throw new NotFoundException("Customer not found");
		}
	}

	@Override
	public String placeOrder(int custId, Orders o) throws AlreadyExistsException {

		if (cRepo.existsById(custId)) {
			Customer c = cRepo.findById(custId).get();
			if (sRepo.existsById(o.getSeller().getSellerId())) {
				Seller s1 = sRepo.findById(o.getSeller().getSellerId()).get();
				if (s1.getProduct().contains(o.getProduct())) {
					// Orders o1 = new Orders(o.getOrderId(), o.getCustomer(), o.getSeller(),
					// o.getProduct(), o.getDeliveryDate());
					o.setCustomer(c);
					ordersServ.saveOrder(o);
					c.addToCustOrders(o);
					cRepo.save(c);
					return "Order placed successfully";
				} else {
					return "Seller " + o.getSeller().getSellerId() + " doesn't sell product "
							+ o.getProduct().getProdId();
				}
			} else {
				return "Seller " + o.getSeller().getSellerId() + " doesn't exist";
			}
////			Orders o1 = new Orders(o.getOrderId(), o.getCustomer(), o.getSeller(), o.getProduct(), o.getDeliveryDate());
////			oRepo.save(o1);
//			c.addToCustOrders(o1);
//			cRepo.save(c);
//			return "Order placed successfully";
		} else {
			return "Customer with given id does not exist";
		}
	}

	@Override
	public String addWishlist(int custId, Product p) throws AlreadyExistsException {

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

		if (cRepo.existsById(custId)) {
			Customer c = cRepo.findById(custId).get();
			Product p1 = pRepo.findById(p.getProdId()).get();
			if (c.getWishlist().contains(p1))
				throw new AlreadyExistsException("Product already exists");
			c.addToWishlist(p1);
			cRepo.save(c);
			return "wishlist updated successfully";
		} else {
			return "Customer with given id does not exist";
		}
	}

	@Override
	public String deleteOrder(int custId, int orderId) throws NotFoundException {
		if (cRepo.existsById(custId)) {
			Customer customer = cRepo.findById(custId).get();
			List<Orders> custOrders = customer.getCustOrders();
			if (oRepo.existsById(orderId)) {
				Orders orders = oRepo.findById(orderId).get();
				if (custOrders.contains(orders)) {
					custOrders.remove(orders);
					customer.setCustOrders(custOrders);
					cRepo.save(customer);
					ordersServ.delete(orderId);
					return "Order deleted successfully";

				} else {
					throw new NotFoundException("Customer " + custId + " hasn't placed the order " + orderId);
				}

			} else {
				throw new NotFoundException("Order with id " + orderId + " doesn't exist.");
			}

		} else {
			throw new NotFoundException("Customer with id: " + custId + " doesn't exist.");
		}
	}

}

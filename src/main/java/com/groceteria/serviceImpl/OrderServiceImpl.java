package com.groceteria.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groceteria.entity.Cart;
import com.groceteria.entity.Order;
import com.groceteria.entity.User;
import com.groceteria.exception.ResourceNotFoundException;
import com.groceteria.repository.CartRepository;
import com.groceteria.repository.OrderRepository;
import com.groceteria.service.CartService;
import com.groceteria.service.ItemService;
import com.groceteria.service.OrderService;
import com.groceteria.service.UserService;


import jakarta.transaction.Transactional;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private static final String ORDER_PLACED = "Placed";

	
	@Autowired
	public OrderRepository orderRepository;
	
	@Autowired
	public ItemService itemService;
	
	@Autowired
	public CartService cartService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public CartRepository c;
	
	

	public OrderServiceImpl(OrderRepository orderRepository, ItemService itemService, CartService cartService,
			UserService userService, CartRepository c) {
		super();
		this.orderRepository = orderRepository;
		this.itemService = itemService;
		this.cartService = cartService;
		this.userService = userService;
		this.c = c;
	}

	@Override
	public Order addOrder(Order order, Integer userId, long cartId) {
		Cart cart = cartService.getCartById(cartId);
		User user = userService.getUserById(userId);
		order.setTotalPrice(order.getMrpPrice() * cart.getQuantity());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderDate(order.getOrderDate());
		order.setMrpPrice(order.getMrpPrice());
		order.setQuantity(cart.getQuantity());
		
		order.setUser(user);
		Order o = orderRepository.save(order);
		c.deleteById(cartId);
		return o;
	}

	@Override
	public Order getOrderById(long orderId) {
		return orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));

	}

	@Override
	public Order updateOrder(Order order, long orderId) {
		Order existingOrder = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
		existingOrder.setTotalPrice(order.getMrpPrice());
		existingOrder.setPaymentStatus(order.getPaymentStatus());
		existingOrder.setMrpPrice(order.getMrpPrice());
		existingOrder.setOrderStatus(order.getOrderStatus());
		existingOrder.setUser(order.getUser());
		existingOrder.setOrderDate(order.getOrderDate());
		orderRepository.save(existingOrder);
		return existingOrder;
	}

	@Override
	public List<Order> getOrderByUserId(Integer userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		java.util.Date d = new java.util.Date(month, day, year);
		System.out.println(d);
		List<Order> orders = orderRepository.findByUserUserId(userId);
		System.out.println(orders);
		return orderRepository.findByUserUserId(userId);
	}

	@Override
	public Order addOrderItem(Order order, Integer userId) {
		User user = userService.getUserById(userId);
		order.setTotalPrice(order.getTotalPrice());
		order.setPaymentStatus(order.getPaymentStatus());
		order.setOrderStatus(order.getOrderStatus());
		order.setOrderDate(order.getOrderDate());
		order.setUser(user);
		order.setItem(order.getItem());
		Order o = orderRepository.save(order);
		return o;
	}

	@Override
	public void deleteOrder(long orderId) {
		orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order", "Id", orderId));
		orderRepository.deleteById(orderId);

	}

	@Override
	public List<Order> getAllOrders() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date date = new java.util.Date();
		String currentDate = sdf.format(date);
		String[] array = currentDate.split("/");
		int month = Integer.parseInt(array[0]);
		int day = Integer.parseInt(array[1]);
		int year = Integer.parseInt(array[2]);
		java.util.Date d = new java.util.Date(month, day, year);
		System.out.println(d);
		List<Order> orders = orderRepository.findAll();
		System.out.println(orders);
		return orderRepository.findAll();
	}

	


	

}

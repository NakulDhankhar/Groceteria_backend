package com.groceteria.service;

import java.util.List;

import com.groceteria.entity.Order;

public interface OrderService {
	Order addOrder(Order order, Integer userId, long cartId);

	Order getOrderById(long orderId);

	Order updateOrder(Order order, long orderId);

	List<Order> getOrderByUserId(Integer userId);
	
	Order addOrderItem(Order order,Integer userId);
	
	void deleteOrder(long orderId);
	

	List<Order> getAllOrders();
}

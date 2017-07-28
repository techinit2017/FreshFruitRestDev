package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Order;

public interface IOrderDAO extends CrudRepository<Order, Integer> {
	
	Order save(Order order); 

	Order findOne(Integer id);
	
	List<Order> findByBuyerId(String buyerId);
}

package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IOrderDAO;
import com.ffp.data.Order;

@Service
public class OrderService {
	
	@Autowired
	private IOrderDAO orderDao;

	public Order findOne(Integer id) {
		return orderDao.findOne(id);
	}

	public List<Order> findByBuyerId(String buyerId) {
		return orderDao.findByBuyerId(buyerId);
	}

	public Order save(Order order) {
		return orderDao.save(order);
	}

}

package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ffp.data.Order;
import com.ffp.service.OrderService;

@RestController
@RequestMapping(value = "/order")
@CrossOrigin(origins = "*")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/getOrderByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrderByID(@PathVariable("id") Integer id) {
		HttpStatus httpStatus = HttpStatus.OK;
		Order order = orderService.findOne(id);
		if (order == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<Order>(order, httpStatus);
	}
	
	@RequestMapping(value = "/getOrderByBuyerId/{buyerId}", method = RequestMethod.GET)
	public ResponseEntity<List<Order>> getProductByBuyerId(@PathVariable("buyerId") String buyerId) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<Order> orders = orderService.findByBuyerId(buyerId);
		if (orders == null || orders.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<Order>>(orders, httpStatus);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<Void> save(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
		HttpStatus httpStatus = HttpStatus.OK;
		Order newOrder = orderService.save(order);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getOrderByID/{id}").buildAndExpand(newOrder.getId()).toUri());
		return new ResponseEntity<Void>(headers, httpStatus);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
		HttpStatus httpStatus = HttpStatus.OK;
		Order newOrder = orderService.save(order);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getOrderByID/{id}").buildAndExpand(newOrder.getId()).toUri());
		return new ResponseEntity<Void>(headers, httpStatus);
	}
}

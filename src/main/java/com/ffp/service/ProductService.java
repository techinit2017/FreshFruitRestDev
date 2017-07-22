package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IProductDAO;
import com.ffp.data.Product;
import com.ffp.data.UserProfile;

@Service
public class ProductService {
	
	@Autowired
	private IProductDAO productDAO;

	public List<Product> findAll() {
		return productDAO.findAll();
	}

	public Product findOne(long id) {
		return productDAO.findOne(id);
	}

	public List<Product> findByName(String name) {
		return productDAO.findByName(name);
	}

	public List<Product> findByType(String type) {
		return productDAO.findByType(type);
	}

	public List<Product> findBySellerId(String sellerId) {
		return productDAO.findBySellerId(sellerId);
	}

	public List<Product> findByAvailable(long available) {
		return productDAO.findByAvailable(available);
	}
	
	public long count() {
		return productDAO.count();
	}
	
	public Product save(Product product) {
		return productDAO.save(product);
	}
	
	public void delete(Product product) {
		productDAO.delete(product);
	}
	
	public void delete(long id) {
		productDAO.delete(id);
	}

}

package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Product;

public interface IProductDAO extends CrudRepository<Product, Integer> {

	Product save(Product product); 

	Product findOne(Integer id);
	
	List<Product> findByName(String name);
	
	List<Product> findByType(String type);
	
	List<Product> findBySellerId(String sellerId);
	
	List<Product> findByAvailable(int available);

	boolean exists(Integer id); 

	List<Product> findAll();

	void delete(Integer id); 

	void delete(Product product);
	
	long count();

}

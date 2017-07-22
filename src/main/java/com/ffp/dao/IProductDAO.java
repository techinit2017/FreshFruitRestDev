package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Product;

public interface IProductDAO extends CrudRepository<Product, Long> {

	Product save(Product product); 

	Product findOne(Long id);
	
	List<Product> findByName(String name);
	
	List<Product> findByType(String type);
	
	List<Product> findBySellerId(String sellerId);
	
	List<Product> findByAvailable(long available);

	boolean exists(Long id); 

	List<Product> findAll();

	void delete(Long id); 

	void delete(Product product);
	
	long count();

}

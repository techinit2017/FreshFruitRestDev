package com.ffp.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Product;
import com.ffp.data.UserProfile;

public interface IProductDAO extends CrudRepository<Product, Integer> {

	Product save(Product product); 

	Product findOne(Integer id);
	
	List<Product> findByName(String name);
	
	List<Product> findByType(String type);
	
	Page<Product> findByUserProfile(UserProfile profile, Pageable pageable);
	
	List<Product> findByAvailable(Date available);

	boolean exists(Integer id); 

	List<Product> findAll();

	void delete(Integer id); 

	void delete(Product product);
	
	long count();

}

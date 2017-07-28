package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.ProductVariety;

public interface IProductVarietyDAO extends CrudRepository<ProductVariety, Integer> {

	ProductVariety findOne(Integer id);
	
	List<ProductVariety> findAll();
	
	List<ProductVariety> findByProduct(String product);
	
	List<ProductVariety> findByVariety(String variety);
}

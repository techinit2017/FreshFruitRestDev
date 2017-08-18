package com.ffp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ffp.data.ProductVariety;

public interface IProductVarietyDAO extends CrudRepository<ProductVariety, Integer> {

	ProductVariety findOne(Integer id);
	
	List<ProductVariety> findAll();
	
	List<ProductVariety> findByProduct(String product);
	
	List<ProductVariety> findByVariety(String variety);
	
	@Query("SELECT DISTINCT p.product FROM ProductVariety p ")
	List<String> findDistinctProductNames();

	@Query("SELECT DISTINCT p.product FROM ProductVariety p where p.type =?1")
	List<ProductVariety> findByType(String type);
}

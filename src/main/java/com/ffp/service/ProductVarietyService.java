package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IProductVarietyDAO;
import com.ffp.data.ProductVariety;

@Service
public class ProductVarietyService {

	@Autowired 
	private IProductVarietyDAO productVarietyDao;
	
	public ProductVariety findOne(Integer id) {
		return productVarietyDao.findOne(id);
	}

	public List<ProductVariety> findByVariety(String variety) {
		return productVarietyDao.findByVariety(variety);
	}

	public List<ProductVariety> findByProduct(String product) {
		return productVarietyDao.findByProduct(product);
	}

	public List<ProductVariety> findAll() {
		return productVarietyDao.findAll();
	}
	
	public List<String> findDistinctProductNames() {
		return productVarietyDao.findDistinctProductNames();
	}
	
	public List<ProductVariety> findByType(String type) {
		return productVarietyDao.findByType(type);
	}

}

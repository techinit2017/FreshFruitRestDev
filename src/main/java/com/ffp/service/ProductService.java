package com.ffp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ffp.dao.IProductDAO;
import com.ffp.dao.IUserProfileDAO;
import com.ffp.dao.SearchDao;
import com.ffp.data.Product;
import com.ffp.data.SearchProduct;
import com.ffp.data.UserProfile;

@Service
public class ProductService {
	
	@Autowired
	private IProductDAO productDAO;
	@Autowired
	private SearchDao searchDao;
	@Autowired
	private IUserProfileDAO userProfileDao;

	public List<Product> findAll() {
		return productDAO.findAll();
	}

	public Product findOne(Integer id) {
		return productDAO.findOne(id);
	}

	public List<Product> findByName(String name) {
		return productDAO.findByName(name);
	}

	public List<Product> findByType(String type) {
		return productDAO.findByType(type);
	}

	public Page<Product> findBySellerId(String sellerId, Pageable pageable) {
		UserProfile profile = userProfileDao.findOne(Integer.parseInt(sellerId));
		return productDAO.findByUserProfile(profile, pageable);
	}

	public List<Product> findByAvailable(Date available) {
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
	
	public void delete(Integer id) {
		productDAO.delete(id);
	}
	
	public Map<String,Object> findSearch(SearchProduct searchProduct, boolean override) {
		return searchDao.doSearch(searchProduct, override);
	}

}

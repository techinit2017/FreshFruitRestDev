package com.ffp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ffp.data.Product;
import com.ffp.data.SearchProduct;

@Repository
public class SearchDao {

	public SearchDao() {
		super();
	}

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Map<String,Object> doSearch(SearchProduct searchProduct, boolean override) {
//		SessionFactory factory = new Configuration().configure().buildSessionFactory();
//		Session session = factory.openSession();
		//Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		//Criteria criteria = session.createCriteria(Product.class);
		Map<String,Object> returnMap=new HashMap<>();
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		if (searchProduct.getName() != null) {
			criteria.add(Restrictions.like("name", "%" + searchProduct.getName() + "%"));
		}
		if (searchProduct.getType() != null) {
			criteria.add(Restrictions.eq("type", searchProduct.getType()));
		}
		
		if (!override) {
			if (searchProduct.getPriceBelow() != null) {
				criteria.add(Restrictions.le("price", searchProduct.getPriceBelow()));
			}
			if (searchProduct.getPriceAbove() != null) {
				criteria.add(Restrictions.ge("price", searchProduct.getPriceAbove()));
			}
			if (searchProduct.getQuantityAvailable() != null) {
				criteria.add(Restrictions.le("quantityAvailable", searchProduct.getQuantityAvailable()));
			}
			if (searchProduct.getMeasurement() != null) {
				criteria.add(Restrictions.eq("measurement", searchProduct.getMeasurement()));
			}
			if (searchProduct.getGrade() != null) {
				criteria.add(Restrictions.eq("grade", searchProduct.getGrade()));
			}
			if (searchProduct.getCountry() != null) {
				criteria.add(Restrictions.eq("country", searchProduct.getCountry()));
			}
			if (searchProduct.getAvailableBefore() != null) {
				criteria.add(Restrictions.le("available", searchProduct.getAvailableBefore()));
			}
			if (searchProduct.getAvailableAfter() != null) {
				criteria.add(Restrictions.ge("available", searchProduct.getAvailableAfter()));
			}
		}
		
//		List results = hibernateTemplate.findByCriteria(criteria);
		int count = hibernateTemplate.findByCriteria(criteria).size();
		List<?> results = hibernateTemplate.findByCriteria(criteria, searchProduct.getPageNumber(), searchProduct.getPageSize());
		
		returnMap.put("RECORD_COUNT", count);
		returnMap.put("LIST", results);
		return returnMap;
	}
}

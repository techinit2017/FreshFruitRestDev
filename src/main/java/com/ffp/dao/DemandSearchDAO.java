package com.ffp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ffp.data.Demand;
import com.ffp.data.SearchProduct;

@Repository
public class DemandSearchDAO {

	
	public DemandSearchDAO() {
	}

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public Map<String,Object> doSearch(SearchProduct searchProduct, boolean override) {
		Map<String,Object> returnMap=new HashMap<>();
		DetachedCriteria criteria = DetachedCriteria.forClass(Demand.class);
		if (searchProduct.getName() != null) {
			criteria.add(Restrictions.like("variety", "%" + searchProduct.getName() + "%"));
		}
		if (searchProduct.getType() != null) {
			criteria.add(Restrictions.eq("product", searchProduct.getType()));
		}
		
		if (!override) {
			if (searchProduct.getPriceBelow() != null) {
				criteria.add(Restrictions.le("price", searchProduct.getPriceBelow()));
			}
			if (searchProduct.getPriceAbove() != null) {
				criteria.add(Restrictions.ge("price", searchProduct.getPriceAbove()));
			}
			if (searchProduct.getQuantityAvailable() != null) {
				criteria.add(Restrictions.le("quantity", searchProduct.getQuantityAvailable()));
			}
			if (searchProduct.getMeasurement() != null) {
				criteria.add(Restrictions.eq("measurement", searchProduct.getMeasurement()));
			}
			if (searchProduct.getCountry() != null) {
				criteria.add(Restrictions.eq("country", searchProduct.getCountry()));
			}
			if (searchProduct.getAvailableBefore() != null) {
				criteria.add(Restrictions.le("availableDate", searchProduct.getAvailableBefore()));
			}
			if (searchProduct.getAvailableAfter() != null) {
				criteria.add(Restrictions.ge("availableDate", searchProduct.getAvailableAfter()));
			}
		}
		
		int count = hibernateTemplate.findByCriteria(criteria).size();
		List<?> results = hibernateTemplate.findByCriteria(criteria, searchProduct.getPageNumber(), searchProduct.getPageSize());
		
		returnMap.put("RECORD_COUNT", count);
		returnMap.put("LIST", results);
		return returnMap;
	}
}

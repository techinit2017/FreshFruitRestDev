package com.ffp.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.ffp.data.Demand;
import com.ffp.data.Product;

public class SearchDemandDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public List<Demand> doSearch(Demand demand, boolean override) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		if (demand.getProduct() != null) {
			criteria.add(Restrictions.like("product", demand.getProduct()));
		}
		if (demand.getVariety() != null) {
			criteria.add(Restrictions.eq("variety", demand.getVariety()));
		}
		
		List results = hibernateTemplate.findByCriteria(criteria);
		return results;
	}
}

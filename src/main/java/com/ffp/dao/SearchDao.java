package com.ffp.dao;

import java.util.List;

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
	
	public List<Product> doSearch(SearchProduct searchProduct, boolean override) {
//		SessionFactory factory = new Configuration().configure().buildSessionFactory();
//		Session session = factory.openSession();
		//Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		//Criteria criteria = session.createCriteria(Product.class);
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
			if (searchProduct.getAvailable() != null) {
				criteria.add(Restrictions.eq("available", "1"));
			}
			if (searchProduct.getQuantityAvailable() != null) {
				criteria.add(Restrictions.le("quantityAvailable", searchProduct.getQuantityAvailable()));
			}
		}
		List results = hibernateTemplate.findByCriteria(criteria);
		return results;
	}
}

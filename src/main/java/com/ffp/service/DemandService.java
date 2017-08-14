package com.ffp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.DemandSearchDAO;
import com.ffp.dao.IDemandDAO;
import com.ffp.data.Demand;
import com.ffp.data.SearchProduct;

@Service
public class DemandService {
	
	@Autowired
	private IDemandDAO demandDao;
	@Autowired
	private DemandSearchDAO demandSearchDao;

	public List<Demand> findAll() {
		return demandDao.findAll();
	}

	public Demand findOne(Integer id) {
		return demandDao.findOne(id);
	}

	public void delete(Demand demand) {
		demandDao.delete(demand);
	}

	public Demand save(Demand demand) {
		return demandDao.save(demand);
	}

	public Map<String, Object> findSearch(SearchProduct searchProduct, boolean override) {
		return demandSearchDao.doSearch(searchProduct, override);
	}

}

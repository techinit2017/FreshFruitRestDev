package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IDemandDAO;
import com.ffp.data.Demand;

@Service
public class DemandService {
	
	@Autowired
	private IDemandDAO demandDao;

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

}

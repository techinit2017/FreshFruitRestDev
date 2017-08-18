package com.ffp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ffp.dao.DemandSearchDAO;
import com.ffp.dao.IDemandDAO;
import com.ffp.dao.IUserProfileDAO;
import com.ffp.data.Demand;
import com.ffp.data.SearchProduct;
import com.ffp.data.UserProfile;

@Service
public class DemandService {
	
	@Autowired
	private IDemandDAO demandDao;
	@Autowired
	private DemandSearchDAO demandSearchDao;
	@Autowired
	private IUserProfileDAO userProfileDao;

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
	
	public Page<Demand> findByUserProfile(Integer userID, Pageable paegable) {
		UserProfile userProfile = userProfileDao.findOne(userID);
		return demandDao.findByUserProfile(userProfile, paegable);
	}

}

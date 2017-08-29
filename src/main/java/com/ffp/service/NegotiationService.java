package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.INegotiationDAO;
import com.ffp.dao.IUserProfileDAO;
import com.ffp.data.Negotiation;
import com.ffp.data.UserProfile;

@Service
public class NegotiationService {
	
	@Autowired
	private INegotiationDAO negotiationDao;
	@Autowired
	private IUserProfileDAO userProfileDao;

	public List<Negotiation> findAll() {
		return negotiationDao.findAll();
	}

	public Negotiation findById(Integer Id) {
		return negotiationDao.findOne(Id);
	}

	public List<Negotiation> findBySellerId(Integer sellerId) {
		UserProfile userProfile = userProfileDao.findOne(sellerId);
		return negotiationDao.findByUserProfileBySellerId(userProfile);
	}
	
	public List<Negotiation> findByBuyerId(Integer buyerId) {
		UserProfile userProfile = userProfileDao.findOne(buyerId);
		return negotiationDao.findByUserProfileByBuyerId(userProfile);
	}

	public Negotiation save(Negotiation negotiation) {
		return negotiationDao.save(negotiation);
	}

	public List<Negotiation> findByStatus(String status) {
		return negotiationDao.findByStatus(status);
	}

}

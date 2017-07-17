package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IUserProfileDAO;
import com.ffp.data.UserProfile;

@Service
public class UserProfileService {
	
	@Autowired
	private IUserProfileDAO userProfileDao;

	public UserProfile  save(UserProfile userProfile) {
		return userProfileDao.save(userProfile);
	}

	public UserProfile findOne(Long id) {
		return userProfileDao.findOne(id);
	}

	public boolean exists(Long id) {
		return userProfileDao.exists(id);
	}

	public List<UserProfile> findAll() {
		return userProfileDao.findAll();
	}

	public long count() {
		return userProfileDao.count();
	}

	public void delete(Long id) {
		userProfileDao.delete(id);
	}
	
	public List<UserProfile> findByLastName(String lastName) {
		return userProfileDao.findByLastName(lastName);
	}
	
	public UserProfile findByUserName(String userName) {
		return userProfileDao.findByUserName(userName);
	}
	
	public UserProfile findByEmailId(String emailID) {
		return userProfileDao.findByEmailId(emailID);
	}
	
	public UserProfile findByPhoneNumber(String phoneNumber) {
		return userProfileDao.findByPhoneNumber(phoneNumber);
	}
	
	public boolean exists(String userName) {
		return userProfileDao.existsByUserName(userName);
	}
}

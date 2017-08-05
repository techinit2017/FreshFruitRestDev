package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IUserProfileDAO;
import com.ffp.data.UserProfile;
import com.ffp.utils.Utils;

@Service
public class UserProfileService {
	
	@Autowired
	private IUserProfileDAO userProfileDao;

	public UserProfile  save(UserProfile userProfile) {
		return userProfileDao.save(userProfile);
	}

	public UserProfile findOne(Integer id) {
		return userProfileDao.findOne(id);
	}

	public boolean exists(Integer id) {
		return userProfileDao.exists(id);
	}

	public List<UserProfile> findAll() {
		return userProfileDao.findAll();
	}

	public long count() {
		return userProfileDao.count();
	}

	public void delete(Integer id) {
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
	
	public boolean exists(UserProfile userProfile) {
		UserProfile existingUserProfile = userProfileDao.findOne(userProfile.getId());
		if (existingUserProfile == null) {
			return false;
		}
		
		if (!Utils.dateFormatter("yyyy-MM-dd", existingUserProfile.getDob()).equals(Utils.dateFormatter("yyyy-MM-dd", existingUserProfile.getDob()))) {
			return false;
		}
		if (!existingUserProfile.getSecretQuestion().equals(userProfile.getSecretQuestion())) {
			return false;
		}
		if (!existingUserProfile.getSecretAnswer().equals(userProfile.getSecretAnswer())) {
			return false;
		}
		return true;
	}
}

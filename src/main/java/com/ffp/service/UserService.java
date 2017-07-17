package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IUserDAO;
import com.ffp.data.User;

@Service
public class UserService {

	@Autowired
	private IUserDAO userDao;

	public List<User> getUserList() {
		return userDao.findByUser();
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public User findByUserId(int userid) {
		return userDao.findByUserId(userid);
	}
	
	public User findByUserName(String name) {
		return userDao.findByUserName(name);
	}

	public void delete(int userid) {
		userDao.deleteByUserId(userid);
	}
}

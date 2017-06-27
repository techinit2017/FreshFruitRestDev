/*package com.ffp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.ffp.data.User;

@Repository
public class UserDao implements IUserDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<User> getUserList() {
		// TODO Auto-generated method stub
		List<?> list = hibernateTemplate.find("FROM user");
		if (!list.isEmpty()) {
			return (List<User>) list;
		}
		return null;
	}

}
*/
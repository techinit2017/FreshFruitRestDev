package com.ffp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ffp.data.User;

public interface IUserDAO extends CrudRepository<User, Long> {

	@Query("from User")
	List<User> findByUser();

	User save(User user);

	User findByUserId(Integer userid);
	
	User findByUserName(String name);

	void deleteByUserId(Integer userid);

	void deleteAll();

}

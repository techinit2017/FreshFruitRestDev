package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ffp.data.UserProfile;

public interface IUserProfileDAO extends CrudRepository<UserProfile, Integer> {
	
	UserProfile  save(UserProfile entity);

	UserProfile findOne(Integer id); 

	boolean existsById(Integer id); 

	List<UserProfile> findAll(); 

	long count(); 

	void delete(Integer id); 
	
	List<UserProfile> findByLastName(String lastName); 
	
	UserProfile findByUserName(String userName); 
	
	UserProfile findByEmailId(String emailId); 
	
	UserProfile findByPhoneNumber(String phoneNumber); 
	
	boolean existsByUserName(String userName);
}

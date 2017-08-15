package com.ffp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ffp.data.UserProfile;

public interface IUserProfileDAO extends PagingAndSortingRepository<UserProfile, Integer> {
	
	UserProfile  save(UserProfile entity);

	Page<UserProfile> findAll(Pageable pageable);

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
	
	boolean existsByEmailId(String emailId);
}

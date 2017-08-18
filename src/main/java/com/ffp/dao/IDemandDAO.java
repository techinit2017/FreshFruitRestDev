package com.ffp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Demand;
import com.ffp.data.UserProfile;

public interface IDemandDAO extends CrudRepository<Demand, Integer>{

	Demand save(Demand demand); 

	Demand findOne(Integer id); 

	List<Demand> findAll(); 

	void delete(Demand demand);
	
	Page<Demand> findByUserProfile(UserProfile userProfile, Pageable paegable); 
}

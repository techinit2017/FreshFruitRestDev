package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Demand;

public interface IDemandDAO extends CrudRepository<Demand, Integer>{

	Demand save(Demand demand); 

	Demand findOne(Integer id); 

	List<Demand> findAll(); 

	void delete(Demand demand); 
}

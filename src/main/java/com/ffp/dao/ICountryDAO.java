package com.ffp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Country;

public interface ICountryDAO extends CrudRepository<Country, Integer> {
	
	Country findOne(Integer id);
	
	List<Country> findAll();
	
	Country findByCountry(String country);

}

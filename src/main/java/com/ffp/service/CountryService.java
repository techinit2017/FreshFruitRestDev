package com.ffp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.ICountryDAO;
import com.ffp.data.Country;

@Service
public class CountryService {

	@Autowired
	private ICountryDAO countryDao;

	public Country findOne(Integer id) {
		return countryDao.findOne(id);
	}

	public Country findByCountry(String country) {
		return countryDao.findByCountry(country);
	}

	public List<Country> findAll() {
		return countryDao.findAll();
	}
}

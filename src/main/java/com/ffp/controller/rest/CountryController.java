package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ffp.data.Country;
import com.ffp.service.CountryService;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "/getCountryByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Country> getCountryByID(@PathVariable("id") Integer id) {
		Country country = countryService.findOne(id);
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCountryByCountry/{countryName}", method = RequestMethod.GET)
	public ResponseEntity<Country> getCountryByCountry(@PathVariable("countryName") String countryName) {
		Country country = countryService.findByCountry(countryName);
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllCountry", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> getAllCountry() {
		List<Country> allCountry = countryService.findAll();
		return new ResponseEntity<List<Country>>(allCountry, HttpStatus.OK);
	}
}

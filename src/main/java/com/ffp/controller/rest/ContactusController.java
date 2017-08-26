package com.ffp.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ffp.data.Contactus;
import com.ffp.service.ContactusService;


@RestController
@RequestMapping(value = "/contactus")
@CrossOrigin(origins = "*")
public class ContactusController {

	@Autowired
	private ContactusService contactusService;
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Contactus> create(@RequestBody Contactus contactus) {
		Contactus newContactus = contactusService.save(contactus);

		if (newContactus != null) {
			return new ResponseEntity<Contactus>(newContactus, HttpStatus.OK);
		} else {
			return new ResponseEntity<Contactus>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}

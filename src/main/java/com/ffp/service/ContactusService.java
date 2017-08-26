package com.ffp.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ffp.dao.IContactusDAO;

import com.ffp.data.Contactus;



@Service
public class ContactusService {
	
	@Autowired
	private IContactusDAO contactDAO;
	

	

	public Contactus save(Contactus contactus) {
		return contactDAO.save(contactus);
	}

	

}

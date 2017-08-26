package com.ffp.dao;


import org.springframework.data.repository.CrudRepository;


import com.ffp.data.Contactus;


public interface IContactusDAO extends CrudRepository<Contactus, Integer>{

	Contactus save(Contactus contact); 

	
}

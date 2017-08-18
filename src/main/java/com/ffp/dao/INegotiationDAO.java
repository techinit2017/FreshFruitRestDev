//package com.ffp.dao;
//
//import java.util.List;
//
//import org.springframework.data.repository.CrudRepository;
//
//import com.ffp.data.Negotiation;
//import com.ffp.data.UserProfile;
//
//public interface INegotiationDAO extends CrudRepository<Negotiation, Integer>{
//
//	Negotiation save(Negotiation negotiation);
//
//	Negotiation findOne(Integer id); 
//
//	List<Negotiation> findAll();
//	
//	List<Negotiation> findByUserProfileByBuyerId(UserProfile userProfileByBuyerId);
//	
//	List<Negotiation> findByUserProfileBySellerId(UserProfile userProfileBySellerId);
//	
////	List<Negotiation> findByStatus();
//
//}

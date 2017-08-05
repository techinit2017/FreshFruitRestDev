package com.ffp.dao;

import org.springframework.data.repository.CrudRepository;

import com.ffp.data.Approval;

public interface IApprovalDAO extends CrudRepository<Approval, Integer> {
	
	Approval save (Approval approval);

	Approval findOne(Integer id);
	
}

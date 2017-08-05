package com.ffp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffp.dao.IApprovalDAO;
import com.ffp.data.Approval;

@Service
public class ApprovalService {

	@Autowired
	private IApprovalDAO approvalDao;
	
	public Approval save (Approval approval) {
		return approvalDao.save(approval);
	}

	public Approval findOne(Integer id) {
		return approvalDao.findOne(id);
	}
}

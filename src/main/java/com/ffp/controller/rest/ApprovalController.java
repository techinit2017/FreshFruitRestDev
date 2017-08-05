package com.ffp.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ffp.data.Approval;
import com.ffp.service.ApprovalService;
import com.ffp.service.UserProfileService;

@RestController
@RequestMapping(value = "/approval")
@CrossOrigin(origins = "*")
public class ApprovalController {

	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private UserProfileService profileService;
	
	@RequestMapping(value = "/getApprovalByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Approval> getApprovalByID(@PathVariable("id") Integer id) {
		Approval approval = approvalService.findOne(id);
		return new ResponseEntity<Approval>(approval, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ResponseEntity<Void> approve(@RequestBody Approval approval, UriComponentsBuilder ucBuilder) {
		Approval newApproval = approvalService.save(approval);
		profileService.save(approval.getUserProfile());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getApprovalByID/{id}").buildAndExpand(newApproval.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.OK);
	}
}

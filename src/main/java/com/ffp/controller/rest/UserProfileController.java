package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ffp.data.UserProfile;
import com.ffp.service.UserProfileService;
import com.ffp.utils.FFPExceptionHandler;

@RestController
@RequestMapping(value = "/profile")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@RequestMapping(value = "/getAllUsersProfile", method = RequestMethod.GET)
	public ResponseEntity<List<UserProfile>> getAllUsersProfile() {

		List<UserProfile> allUserProfiles = userProfileService.findAll();
		return new ResponseEntity<List<UserProfile>>(allUserProfiles, HttpStatus.OK);
	}
	@ExceptionHandler({ FFPExceptionHandler.class })
	@RequestMapping(value = "/getUserProfileByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByID(@PathVariable("id") long id) {
		System.out.println("id==" + id);
		UserProfile userProfile = userProfileService.findOne(id);
		UserProfile userProfile2 = new UserProfile(userProfile.getUserName(), userProfile.getPassword(),
				userProfile.getUserType());
		return new ResponseEntity<UserProfile>(userProfile2, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUsersProfileByLastName/{lastName}", method = RequestMethod.GET)
	public ResponseEntity<List<UserProfile>> getUsersProfileByLastName(@PathVariable("lastName") String lastName) {

		List<UserProfile> allUserProfilesByLastName = userProfileService.findByLastName(lastName);
		return new ResponseEntity<List<UserProfile>>(allUserProfilesByLastName, HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		if (!userProfileService.exists(userProfile.getUserName())) {
			UserProfile profile = userProfileService.save(userProfile);
			headers.setLocation(ucBuilder.path("/getUserProfileByID/{id}").buildAndExpand(profile.getId()).toUri());
			status = HttpStatus.CREATED;
		} else {
			status = HttpStatus.CONFLICT;
		}
		return new ResponseEntity<Void>(headers, status);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<Void> save(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
		UserProfile profile = userProfileService.save(userProfile);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/getUserProfileByID/{id}").buildAndExpand(profile.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/getUserProfileByUserName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByUserName(@PathVariable("userName") String userName) {
		UserProfile userProfile = userProfileService.findByUserName(userName);
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserProfileByEmailID/{emailID}/", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByEmailID(@PathVariable("emailID") String emailID) {
		UserProfile userProfile = userProfileService.findByEmailId(emailID);
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserProfileByPhoneNumber/{phoneNumber}/", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		UserProfile userProfile = userProfileService.findByPhoneNumber(phoneNumber);
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}

}

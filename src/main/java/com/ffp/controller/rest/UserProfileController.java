package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ffp.data.UserProfile;
import com.ffp.service.UserProfileService;
import com.ffp.utils.Encryption;
import com.ffp.utils.FFPExceptionHandler;

@RestController
@RequestMapping(value = "/profile")
@CrossOrigin(origins = "*")
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
	public ResponseEntity<UserProfile> getUserProfileByID(@PathVariable("id") Integer id) {
		System.out.println("id==" + id);
		UserProfile userProfile = userProfileService.findOne(id);
		// UserProfile userProfile2 = new UserProfile(userProfile.getUserName(),
		// userProfile.getPassword(),
		// userProfile.getUserType());
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUsersProfileByLastName/{lastName}", method = RequestMethod.GET)
	public ResponseEntity<List<UserProfile>> getUsersProfileByLastName(@PathVariable("lastName") String lastName) {

		List<UserProfile> allUserProfilesByLastName = userProfileService.findByLastName(lastName);
		return new ResponseEntity<List<UserProfile>>(allUserProfilesByLastName, HttpStatus.OK);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<UserProfile> create(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		if (!userProfileService.exists(userProfile.getUserName())) {
			// encrypt password and before saving
			String password = Encryption.encrypt(userProfile.getPassword());
			userProfile.setPassword(password);

			UserProfile profile = userProfileService.save(userProfile);
			headers.setLocation(ucBuilder.path("/getUserProfileByID/{id}").buildAndExpand(profile.getId()).toUri());
			status = HttpStatus.CREATED;
			return new ResponseEntity<UserProfile>(profile, status);
		} else {
			status = HttpStatus.CONFLICT;
			return new ResponseEntity<UserProfile>(status);
		}
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
	
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ResponseEntity<Void> validate(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		if (userProfileService.exists(userProfile)) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.NOT_ACCEPTABLE;
		}
		return new ResponseEntity<Void>(headers, status);
	}

	/**
	 * User Login Service
	 * 
	 * @param userProfile
	 * @param ucBuilder
	 * @return {@link UserProfile}
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<UserProfile> doLogin(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		UserProfile profile = userProfileService.findByUserName(userProfile.getUserName());
		if (profile != null && profile.getId() > 0) {
			if (Encryption.encrypt(userProfile.getPassword()).equalsIgnoreCase(profile.getPassword())) {
				return new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
			} else {
				status = HttpStatus.NOT_FOUND;
				return new ResponseEntity<>(headers, status);
			}
		} else {
			status = HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(headers, status);
		}

	}
}

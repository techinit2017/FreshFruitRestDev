package com.ffp.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.ffp.data.PageParam;
import com.ffp.data.UserProfile;
import com.ffp.service.UserProfileService;
import com.ffp.utils.Encryption;
import com.ffp.utils.FFPExceptionHandler;

@RestController
@RequestMapping(value = "/profile")
@CrossOrigin(origins = "*",exposedHeaders ="RECORD_COUNT")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@RequestMapping(value = "/getAllUsersProfile", method = RequestMethod.GET)
	public ResponseEntity<List<UserProfile>> getAllUsersProfile() {
		HttpStatus httpStatus = HttpStatus.OK;
		List<UserProfile> allUserProfiles = userProfileService.findAll();
		if (allUserProfiles == null || allUserProfiles.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<UserProfile>>(allUserProfiles, httpStatus);
	}

	@ExceptionHandler({ FFPExceptionHandler.class })
	@RequestMapping(value = "/getUserProfileByID/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByID(@PathVariable("id") Integer id) {
		HttpStatus httpStatus = HttpStatus.OK;
		System.out.println("id==" + id);
		UserProfile userProfile = userProfileService.findOne(id);
		if (userProfile == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		// UserProfile userProfile2 = new UserProfile(userProfile.getUserName(),
		// userProfile.getPassword(),
		// userProfile.getUserType());
		return new ResponseEntity<UserProfile>(userProfile, httpStatus);
	}

	@RequestMapping(value = "/getUsersProfileByLastName/{lastName}", method = RequestMethod.GET)
	public ResponseEntity<List<UserProfile>> getUsersProfileByLastName(@PathVariable("lastName") String lastName) {
		HttpStatus httpStatus = HttpStatus.OK;
		List<UserProfile> allUserProfilesByLastName = userProfileService.findByLastName(lastName);
		if (allUserProfilesByLastName == null || allUserProfilesByLastName.isEmpty()) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<List<UserProfile>>(allUserProfilesByLastName, httpStatus);
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<UserProfile> create(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
//		HttpHeaders headers = new HttpHeaders();
		HttpStatus status;
		if (!userProfileService.exists(userProfile.getUserName(), userProfile.getEmailId())) {
			// encrypt password and before saving
			String password = Encryption.encrypt(userProfile.getPassword());
			userProfile.setPassword(password);

			UserProfile profile = userProfileService.save(userProfile);
//			headers.setLocation(ucBuilder.path("/getUserProfileByID/{id}").buildAndExpand(profile.getId()).toUri());
			status = HttpStatus.CREATED;
			return new ResponseEntity<UserProfile>(profile,status);
		} else {
			status = HttpStatus.CONFLICT;
			return new ResponseEntity<UserProfile>(status);
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<UserProfile> save(@RequestBody UserProfile userProfile, UriComponentsBuilder ucBuilder) {
		UserProfile profile = userProfileService.save(userProfile);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/getUserProfileByID/{id}").buildAndExpand(profile.getId()).toUri());
		return new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
	}

	@RequestMapping(value = "/getUserProfileByUserName/{userName}", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByUserName(@PathVariable("userName") String userName) {
		HttpStatus httpStatus = HttpStatus.OK;
		UserProfile userProfile = userProfileService.findByUserName(userName);
		if (userProfile == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<UserProfile>(userProfile, httpStatus);
	}

	@RequestMapping(value = "/getUserProfileByEmailID/{emailID}/", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByEmailID(@PathVariable("emailID") String emailID) {
		HttpStatus httpStatus = HttpStatus.OK;
		UserProfile userProfile = userProfileService.findByEmailId(emailID);
		if (userProfile == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<UserProfile>(userProfile, httpStatus);
	}

	@RequestMapping(value = "/getUserProfileByPhoneNumber/{phoneNumber}/", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		HttpStatus httpStatus = HttpStatus.OK;
		UserProfile userProfile = userProfileService.findByPhoneNumber(phoneNumber);
		if (userProfile == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<UserProfile>(userProfile, httpStatus);
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

	@RequestMapping(value = "/getUserProfileByUniqueIdentity/{uniqueIdentity}/", method = RequestMethod.GET)
	public ResponseEntity<UserProfile> getUserProfileByUniqueIdentity(
			@PathVariable("uniqueIdentity") String uniqueIdentity) {
		HttpStatus httpStatus = HttpStatus.OK;
		UserProfile userProfile = userProfileService.findByUniqueIdentity(uniqueIdentity);
		if (userProfile == null) {
			httpStatus = HttpStatus.NOT_FOUND;
		}
		return new ResponseEntity<UserProfile>(userProfile, httpStatus);
	}

	@RequestMapping(value = "/getAllUsersProfileByPageRequest", method = RequestMethod.POST)
	public ResponseEntity<List<UserProfile>> getAllUsersProfile(@RequestBody PageParam pageParam) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.OK;
		Page<UserProfile> allUserProfiles = userProfileService.findAllByPageRequest(pageParam.getPageRequest());
		if(allUserProfiles!=null ){
			List<UserProfile> userprofilelist= allUserProfiles.getContent();
			headers.set("RECORD_COUNT",String.valueOf( allUserProfiles.getTotalElements()));
			httpStatus = HttpStatus.OK;
			return new ResponseEntity<List<UserProfile>>(userprofilelist,headers, httpStatus);
		}
		else  {
			httpStatus = HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(httpStatus);
		}
		
	}

//	@RequestMapping(value = "/test", method = RequestMethod.POST)
//	public ResponseEntity<PageRequest> test() {
//		PageRequest pageRequest = new PageRequest(1, 10, new Sort(Sort.Direction.DESC, "description"));
//		return new ResponseEntity<PageRequest>(pageRequest, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/test1", method = RequestMethod.POST)
//	public ResponseEntity<List<UserProfile>> test(@RequestBody PageParam pageParam) {
//		PageRequest pageRequest = null;
//		
//		if (pageParam.getSort() != null && !pageParam.getSort().isEmpty()) {
//			List<Order> orderlist =new ArrayList<>();
//			for (com.ffp.data.Sort sort : pageParam.getSort()) {
//				orderlist.add(new Order(getDirection(sort.getDirection()), sort.getProperty()));
//			}
//			pageRequest = new PageRequest(pageParam.getPageNumber(), pageParam.getPageSize(), new Sort(orderlist));
//		} else {
//			pageRequest = new PageRequest(pageParam.getPageNumber(), pageParam.getPageSize());
//		}
		
//			return getAllUsersProfile(pageParam.getPageRequest());
		
		//return new ResponseEntity<Pageable>(pageRequest, HttpStatus.OK);
//	}

//	private Sort.Direction getDirection(final String direction) {
//		if (direction.equalsIgnoreCase("DESC")) {
//			return Sort.Direction.DESC;
//		} else {
//			return Sort.Direction.ASC;
//		}
//	}
}

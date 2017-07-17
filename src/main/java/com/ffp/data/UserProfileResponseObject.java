package com.ffp.data;

public class UserProfileResponseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userName;
	private String password;
	private String userType;
	public UserProfileResponseObject(Long id, String userName, String password, String userType) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	
}

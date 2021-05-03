package com.reimbursement.dto;

import com.reimbursement.models.ERS_User_Roles;

public class ERS_UsersDTO {
	
	private String ers_userName;
	private String ers_password;
	private String userEmail;
	private String userFirstName;
	private String userLastName;
	private String userRole;
	//private ERS_User_Roles user_roleId;
	
	public ERS_UsersDTO() {
		super();
	}
	
	public ERS_UsersDTO(String ers_userName, String ers_password, String userEmail, String userFirstName,
			String userLastName, String userRole) {
		super();
		this.ers_userName = ers_userName;
		this.ers_password = ers_password;
		this.userEmail = userEmail;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userRole = userRole;
	}

	public ERS_UsersDTO(String ers_userName, String ers_password) {
		super();
		this.ers_userName = ers_userName;
		this.ers_password = ers_password;
	}

	public String getErs_userName() {
		return ers_userName;
	}

	public void setErs_userName(String ers_userName) {
		this.ers_userName = ers_userName;
	}

	public String getErs_password() {
		return ers_password;
	}

	public void setErs_password(String ers_password) {
		this.ers_password = ers_password;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

}

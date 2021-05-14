package com.reimbursement.dto;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ers_password == null) ? 0 : ers_password.hashCode());
		result = prime * result + ((ers_userName == null) ? 0 : ers_userName.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ERS_UsersDTO other = (ERS_UsersDTO) obj;
		if (ers_password == null) {
			if (other.ers_password != null)
				return false;
		} else if (!ers_password.equals(other.ers_password))
			return false;
		if (ers_userName == null) {
			if (other.ers_userName != null)
				return false;
		} else if (!ers_userName.equals(other.ers_userName))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userFirstName == null) {
			if (other.userFirstName != null)
				return false;
		} else if (!userFirstName.equals(other.userFirstName))
			return false;
		if (userLastName == null) {
			if (other.userLastName != null)
				return false;
		} else if (!userLastName.equals(other.userLastName))
			return false;
		if (userRole == null) {
			if (other.userRole != null)
				return false;
		} else if (!userRole.equals(other.userRole))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ERS_UsersDTO [ers_userName=" + ers_userName + ", ers_password=" + ers_password + ", userEmail="
				+ userEmail + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userRole="
				+ userRole + "]";
	}
	
	

}

package com.reimbursement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ERS_Users", uniqueConstraints = @UniqueConstraint(name = "ers_users_UNv1", columnNames = {
		"ers_userName", "userEmail" }))
public class ERS_Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ers_users_id")
	private int ers_users_id;

	@Column(name = "ers_userName", length = 50)
	private String ers_userName;

	@Column(name = "ers_password", length = 50)
	private String ers_password;

	@Column(name = "userEmail", length = 150)
	private String userEmail;

	@Column(name = "userFirstName", length = 100)
	private String userFirstName;

	@Column(name = "userLastName", length = 100)
	private String userLastName;

	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private ERS_User_Roles user_roleId;

	public ERS_Users() {
		super();
	}

	public ERS_Users(String ers_userName, String ers_password, String userEmail, String userFirstName,
			String userLastName, ERS_User_Roles user_roleId) {
		super();
		this.ers_userName = ers_userName;
		this.ers_password = ers_password;
		this.userEmail = userEmail;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.user_roleId = user_roleId;
	}

	public int getErs_users_id() {
		return ers_users_id;
	}

	public void setErs_users_id(int ers_users_id) {
		this.ers_users_id = ers_users_id;
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

	public ERS_User_Roles getUser_roleId() {
		return user_roleId;
	}

	public void setUser_roleId(ERS_User_Roles user_roleId) {
		this.user_roleId = user_roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ers_password == null) ? 0 : ers_password.hashCode());
		result = prime * result + ((ers_userName == null) ? 0 : ers_userName.hashCode());
		result = prime * result + ers_users_id;
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userFirstName == null) ? 0 : userFirstName.hashCode());
		result = prime * result + ((userLastName == null) ? 0 : userLastName.hashCode());
		result = prime * result + ((user_roleId == null) ? 0 : user_roleId.hashCode());
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
		ERS_Users other = (ERS_Users) obj;
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
		if (ers_users_id != other.ers_users_id)
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
		if (user_roleId == null) {
			if (other.user_roleId != null)
				return false;
		} else if (!user_roleId.equals(other.user_roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ERS_Users [ers_users_id=" + ers_users_id + ", ers_userName=" + ers_userName + ", ers_password="
				+ ers_password + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userEmail="
				+ userEmail + ", user_roleId=" + user_roleId + "]";
	}

}

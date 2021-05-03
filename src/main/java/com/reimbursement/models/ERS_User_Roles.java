package com.reimbursement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ERS_User_Roles")
public class ERS_User_Roles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ers_user_roles_id")
	private int ers_user_roles_id;
	
	@Column(name="user_role", length = 10)
	private String user_role;

	public ERS_User_Roles() {
		super();
	}
	
	public ERS_User_Roles(int ers_user_roles_id) {
		super();
		this.ers_user_roles_id = ers_user_roles_id;
	}

	public ERS_User_Roles(String user_role) {
		super();
		this.user_role = user_role;
	}

	public int getErs_user_roles_id() {
		return ers_user_roles_id;
	}

	public void setErs_user_roles_id(int ers_user_roles_id) {
		this.ers_user_roles_id = ers_user_roles_id;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ers_user_roles_id;
		result = prime * result + ((user_role == null) ? 0 : user_role.hashCode());
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
		ERS_User_Roles other = (ERS_User_Roles) obj;
		if (ers_user_roles_id != other.ers_user_roles_id)
			return false;
		if (user_role == null) {
			if (other.user_role != null)
				return false;
		} else if (!user_role.equals(other.user_role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ERS_User_Roles [ers_user_roles_id=" + ers_user_roles_id + ", user_role=" + user_role + "]";
	}

}

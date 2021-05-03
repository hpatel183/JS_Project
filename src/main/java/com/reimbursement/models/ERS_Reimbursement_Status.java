package com.reimbursement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ERS_Reimbursement_Status")
public class ERS_Reimbursement_Status {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reimb_status_id")
	private int reimb_status_id;
	
	@Column(name="reimb_status", length = 10)
	private String reimb_status;

	public ERS_Reimbursement_Status() {
		super();
	}

	public ERS_Reimbursement_Status(String reimb_status) {
		super();
		this.reimb_status = reimb_status;
	}

	public int getReimb_status_id() {
		return reimb_status_id;
	}

	public void setReimb_status_id(int reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public String getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(String reimb_status) {
		this.reimb_status = reimb_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reimb_status == null) ? 0 : reimb_status.hashCode());
		result = prime * result + reimb_status_id;
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
		ERS_Reimbursement_Status other = (ERS_Reimbursement_Status) obj;
		if (reimb_status == null) {
			if (other.reimb_status != null)
				return false;
		} else if (!reimb_status.equals(other.reimb_status))
			return false;
		if (reimb_status_id != other.reimb_status_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ERS_Reimbursement_Status [reimb_status_id=" + reimb_status_id + ", reimb_status=" + reimb_status + "]";
	}
	
}

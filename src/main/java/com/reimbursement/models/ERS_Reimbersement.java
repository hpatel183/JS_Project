package com.reimbursement.models;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="ERS_Reimbersement")
public class ERS_Reimbersement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	private int reimb_id;
	
	@Column(name="reimb_amount")
	private int reimb_amount;
	
	@Column(name="reimb_submitted")
	private Timestamp reimb_submitted;
	
	@Column(name="reimb_resolved")
	private Timestamp reimb_resolved;
	
	@Column(name="reimb_description", length = 250)
	private String reimb_description;
	
	@Lob
	@Column(name="reimb_receipt")
	private byte[] reimb_receipt;
	
	@ManyToOne
	@JoinColumn(name ="reimb_author")
	private ERS_Users reimb_author;
	
	@ManyToOne
	@JoinColumn(name ="reimb_resolver")
	private ERS_Users reimb_resolver;
	
	@ManyToOne
	@JoinColumn(name ="reimb_status_id")
	private ERS_Reimbursement_Status reimb_status_id;
	
	@ManyToOne
	@JoinColumn(name ="reimb_type_id")
	private ERS_Reimbursement_Type reimb_type_id;

	public ERS_Reimbersement() {
		super();
	}

	/*
	 * //no receipt public ERS_Reimbersement(int reimb_amount, Timestamp
	 * reimb_submitted, Timestamp reimb_resolved, String reimb_description,
	 * ERS_Users reimb_author, ERS_Users reimb_resolver, ERS_Reimbursement_Status
	 * reimb_status_id, ERS_Reimbursement_Type reimb_type_id) { super();
	 * this.reimb_amount = reimb_amount; this.reimb_submitted = reimb_submitted;
	 * this.reimb_resolved = reimb_resolved; this.reimb_description =
	 * reimb_description; this.reimb_author = reimb_author; this.reimb_resolver =
	 * reimb_resolver; this.reimb_status_id = reimb_status_id; this.reimb_type_id =
	 * reimb_type_id; }
	 */	

	public ERS_Reimbersement(int reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, byte[] reimb_receipt, ERS_Users reimb_author, ERS_Users reimb_resolver,
			ERS_Reimbursement_Status reimb_status_id, ERS_Reimbursement_Type reimb_type_id) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	public ERS_Reimbersement(int reimb_id, int reimb_amount, Timestamp reimb_submitted, Timestamp reimb_resolved,
			String reimb_description, byte[] reimb_receipt, ERS_Users reimb_author, ERS_Users reimb_resolver,
			ERS_Reimbursement_Status reimb_status_id, ERS_Reimbursement_Type reimb_type_id) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_id = reimb_status_id;
		this.reimb_type_id = reimb_type_id;
	}

	public int getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}

	public int getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public Timestamp getReimb_submitted() {
		return reimb_submitted;
	}

	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public Timestamp getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_description() {
		return reimb_description;
	}

	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}

	public byte[] getReimb_receipt() {
		return reimb_receipt;
	}

	public void setReimb_receipt(byte[] reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public ERS_Users getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(ERS_Users reimb_author) {
		this.reimb_author = reimb_author;
	}

	public ERS_Users getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(ERS_Users reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public ERS_Reimbursement_Status getReimb_status_id() {
		return reimb_status_id;
	}

	public void setReimb_status_id(ERS_Reimbursement_Status reimb_status_id) {
		this.reimb_status_id = reimb_status_id;
	}

	public ERS_Reimbursement_Type getReimb_type_id() {
		return reimb_type_id;
	}

	public void setReimb_type_id(ERS_Reimbursement_Type reimb_type_id) {
		this.reimb_type_id = reimb_type_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimb_amount;
		result = prime * result + ((reimb_author == null) ? 0 : reimb_author.hashCode());
		result = prime * result + ((reimb_description == null) ? 0 : reimb_description.hashCode());
		result = prime * result + reimb_id;
		result = prime * result + Arrays.hashCode(reimb_receipt);
		result = prime * result + ((reimb_resolved == null) ? 0 : reimb_resolved.hashCode());
		result = prime * result + ((reimb_resolver == null) ? 0 : reimb_resolver.hashCode());
		result = prime * result + ((reimb_status_id == null) ? 0 : reimb_status_id.hashCode());
		result = prime * result + ((reimb_submitted == null) ? 0 : reimb_submitted.hashCode());
		result = prime * result + ((reimb_type_id == null) ? 0 : reimb_type_id.hashCode());
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
		ERS_Reimbersement other = (ERS_Reimbersement) obj;
		if (reimb_amount != other.reimb_amount)
			return false;
		if (reimb_author == null) {
			if (other.reimb_author != null)
				return false;
		} else if (!reimb_author.equals(other.reimb_author))
			return false;
		if (reimb_description == null) {
			if (other.reimb_description != null)
				return false;
		} else if (!reimb_description.equals(other.reimb_description))
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (!Arrays.equals(reimb_receipt, other.reimb_receipt))
			return false;
		if (reimb_resolved == null) {
			if (other.reimb_resolved != null)
				return false;
		} else if (!reimb_resolved.equals(other.reimb_resolved))
			return false;
		if (reimb_resolver == null) {
			if (other.reimb_resolver != null)
				return false;
		} else if (!reimb_resolver.equals(other.reimb_resolver))
			return false;
		if (reimb_status_id == null) {
			if (other.reimb_status_id != null)
				return false;
		} else if (!reimb_status_id.equals(other.reimb_status_id))
			return false;
		if (reimb_submitted == null) {
			if (other.reimb_submitted != null)
				return false;
		} else if (!reimb_submitted.equals(other.reimb_submitted))
			return false;
		if (reimb_type_id == null) {
			if (other.reimb_type_id != null)
				return false;
		} else if (!reimb_type_id.equals(other.reimb_type_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ERS_Reimbersement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", reimb_receipt=" + Arrays.toString(reimb_receipt) + ", reimb_author=" + reimb_author
				+ ", reimb_resolver=" + reimb_resolver + ", reimb_status_id=" + reimb_status_id + ", reimb_type_id="
				+ reimb_type_id + "]";
	}

	
}

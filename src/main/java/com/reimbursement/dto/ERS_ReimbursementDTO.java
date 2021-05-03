package com.reimbursement.dto;

import java.sql.Timestamp;

import com.reimbursement.models.ERS_Reimbursement_Status;
import com.reimbursement.models.ERS_Reimbursement_Type;
import com.reimbursement.models.ERS_Users;

public class ERS_ReimbursementDTO {
	
	private int reimb_amount;
	private String reimb_description;
	private byte[] reimb_receipt;
	private String reimType;
	
	public ERS_ReimbursementDTO() {
		super();
	}

	public ERS_ReimbursementDTO(int reimb_amount, String reimb_description, byte[] reimb_receipt, String reimType) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimType = reimType;
	}

	public int getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
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

	public String getReimType() {
		return reimType;
	}

	public void setReimType(String reimType) {
		this.reimType = reimType;
	}

	
}

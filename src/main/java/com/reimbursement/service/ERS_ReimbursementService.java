package com.reimbursement.service;

import java.util.List;

import com.reimbursement.dao.ERS_ReimbursementDAO;
import com.reimbursement.dto.ERS_ReimbursementDTO;
import com.reimbursement.exception.AuthenticationException;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.exception.DatabaseException;
import com.reimbursement.exception.LoginException;
import com.reimbursement.exception.NoResultException;
import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_Users;

public class ERS_ReimbursementService {

	private ERS_ReimbursementDAO reimbersementDAO;

	public ERS_ReimbursementService() {
		this.reimbersementDAO = new ERS_ReimbursementDAO();
	}

	public ERS_ReimbursementService(ERS_ReimbursementDAO reimbersementDAO) {
		this.reimbersementDAO = reimbersementDAO;
	}

	public List<ERS_Reimbersement> getAllreimbursement(ERS_Users user) throws LoginException {

		if (user == null) {
			throw new LoginException("User NOT FOUND");
		}

		System.out.println("Go to DAO");
		List<ERS_Reimbersement> reimbersement = reimbersementDAO.getAllreimbursement(user);
		return reimbersement;
	}

	public ERS_Reimbersement addreimbursement(ERS_Users user, ERS_ReimbursementDTO reimbursementDTO)
			throws BadParameterException, LoginException {
		/*
		 * if(reimbursementDTO.getReimb_description().trim().equals("") ||
		 * reimbursementDTO.getReimb_submitted() == null ||
		 * reimbursementDTO.getReimb_author() == null ||
		 * reimbursementDTO.getReimb_status_id() == null ||
		 * reimbursementDTO.getReimb_type_id() == null) { throw new
		 * BadParameterException("None of the fields should be Blank/NULL"); }
		 */
		if (user == null) {
			throw new LoginException("User NOT Logged In");
		}

		ERS_Reimbersement reimbursement = reimbersementDAO.addReimbursment(user, reimbursementDTO);
		return reimbursement;
	}

	public ERS_Users approveReimbursement(ERS_Users user, String reimID) throws LoginException, DatabaseException, NoResultException, AuthenticationException, BadParameterException {
		if (user == null) {
			throw new LoginException("User NOT Logged In");
		}

		ERS_Users approver = reimbersementDAO.approveReimbursement(user, reimID);

		return approver;
	}

	public ERS_Users denyReimbursement(ERS_Users user, String reimID) throws LoginException, AuthenticationException, BadParameterException {
		if (user == null) {
			throw new LoginException("User NOT Logged In");
		}
		
		ERS_Users denier = reimbersementDAO.denieReimbursement(user, reimID);

		return denier;
	}

}

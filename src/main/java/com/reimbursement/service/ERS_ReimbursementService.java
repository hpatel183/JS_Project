package com.reimbursement.service;

import java.io.InputStream;
import java.util.List;

import com.reimbursement.dao.ERS_ReimbursementDAO;
import com.reimbursement.dto.CleanReimbursement;
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
	private CleanReimbursement cleanDAO;

	public ERS_ReimbursementService() {
		this.reimbersementDAO = new ERS_ReimbursementDAO();
	}

	public ERS_ReimbursementService(ERS_ReimbursementDAO reimbersementDAO) {
		this.reimbersementDAO = reimbersementDAO;
	}

	public List<CleanReimbursement> getAllreimbursement(ERS_Users user) throws LoginException {

		if (user == null) {
			throw new LoginException("User Not Logged In");
		}

		System.out.println("Go to DAO");
		List<CleanReimbursement> reimbersement = reimbersementDAO.getAllreimbursement(user);
		
		return reimbersement;
	}

	public ERS_Reimbersement addreimbursement(ERS_Users user, ERS_ReimbursementDTO reimbursementDTO)
			throws BadParameterException, LoginException {

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

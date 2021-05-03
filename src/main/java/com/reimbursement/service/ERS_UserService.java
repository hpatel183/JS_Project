package com.reimbursement.service;

import java.util.ArrayList;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.reimbursement.dao.ERS_UsersDAO;
import com.reimbursement.dto.ERS_UsersDTO;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.exception.LoginException;
import com.reimbursement.models.ERS_Users;

public class ERS_UserService {
	
	private ERS_UsersDAO userDAO;
	
	public ERS_UserService() {
		this.userDAO = new ERS_UsersDAO();
	}

	public ERS_UserService(ERS_UsersDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public ERS_Users login(ERS_UsersDTO userDTO) throws BadParameterException, LoginException {
		
		if(userDTO.getErs_userName().trim().equals("") || userDTO.getErs_password().trim().equals("")) {
			throw new BadParameterException("Blank UN/PS not allowed");
		}
		
		ERS_Users user = userDAO.getUserByUsernameAndPassword(userDTO);
		
		if(user == null) {
			throw new LoginException("User was not able to login in with the supplied username and password");
		}
		
		return user;
	}

	public ERS_Users addUser(ERS_UsersDTO userDTO) throws BadParameterException, SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		if(userDTO.getErs_userName().trim().equals("") || userDTO.getErs_password().trim().equals("") ||
				userDTO.getUserEmail().trim().equals("") || userDTO.getUserFirstName().trim().equals("")||
				userDTO.getUserLastName().trim().equals("") || userDTO.getUserRole().trim().equals("")) {
			throw new BadParameterException("None of the fields should be Blank/NULL");
		}
		ERS_Users users = userDAO.addUserInfo(userDTO);
		return users;
	}

	public ArrayList<ERS_Users> getAllusers() {
		
		ArrayList<ERS_Users> users = userDAO.getAllUsers();
		
		return users;
	}
	
}

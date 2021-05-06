package com.reimbursement.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.reimbursement.dto.ERS_UsersDTO;
import com.reimbursement.exception.LoginException;
import com.reimbursement.models.ERS_User_Roles;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.utils.SessionUtility;

public class ERS_UsersDAO {

	public ERS_Users getUserByUsernameAndPassword(ERS_UsersDTO userDTO) throws LoginException {

		try {
			Session session = SessionUtility.getSession();

			ERS_Users user;

			System.out.println(userDTO.getErs_userName() + " " + userDTO.getErs_password());
			
			user = (ERS_Users) session.createQuery("FROM ERS_Users WHERE ers_userName=:un AND ers_password=:pw")
					.setParameter("un", userDTO.getErs_userName()).setParameter("pw", hashPassword(userDTO.getErs_password()))
					.getSingleResult();

			System.out.println(user);
			return user;
		} catch (NoResultException e) {
			throw new LoginException("User was not able to login in with the supplied username and password");
		}
	}

	public ERS_Users addUserInfo(ERS_UsersDTO userDTO) throws SecurityException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException, SystemException, LoginException {

		try {
			Session session = SessionUtility.getSession();

			ERS_User_Roles userRole = (ERS_User_Roles) session
					.createQuery("FROM ERS_User_Roles WHERE user_role = :role")
					.setParameter("role", userDTO.getUserRole()).getSingleResult();

			System.out
					.println(userDTO.getErs_userName() + " " + userDTO.getErs_password() + " " + userDTO.getUserEmail()
							+ " " + userDTO.getUserFirstName() + " " + userDTO.getUserLastName() + " " + userRole);

			Transaction tx = session.beginTransaction();

			ERS_Users user = new ERS_Users(userDTO.getErs_userName(), hashPassword(userDTO.getErs_password()), userDTO.getUserEmail(),
					userDTO.getUserFirstName(), userDTO.getUserLastName(), userRole);
			session.save(user);
			tx.commit();
			return user;
		} catch (PersistenceException e) {
			throw new LoginException("User Exists");
		}
	}

	public ArrayList<ERS_Users> getAllUsers() throws LoginException {

		try {
			Session session = SessionUtility.getSession();
			ArrayList<ERS_Users> users = new ArrayList<ERS_Users>();
			users = (ArrayList<ERS_Users>) session.createQuery("FROM ERS_Users").getResultList();

			System.out.println(users);
			return users;

		} catch (NoResultException e) {
			throw new LoginException("No user found.");
		}
	}

	public static String hashPassword(String password) {

		String generatedPassword = null;
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(password.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest();
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;

	}

}

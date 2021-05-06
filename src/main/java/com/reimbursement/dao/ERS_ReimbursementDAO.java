package com.reimbursement.dao;

import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.reimbursement.dto.CleanReimbursement;
import com.reimbursement.dto.ERS_ReimbursementDTO;
import com.reimbursement.exception.AuthenticationException;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.exception.DatabaseException;
import com.reimbursement.exception.LoginException;
import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_Reimbursement_Status;
import com.reimbursement.models.ERS_Reimbursement_Type;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.utils.SessionUtility;

public class ERS_ReimbursementDAO {

	public List<CleanReimbursement> getAllreimbursement(ERS_Users user) throws LoginException {

		try {
		System.out.println("Get Session");
		Session session = SessionUtility.getSession();
		
		List<CleanReimbursement> cleanreimbursements = new ArrayList<>();
		
		
		List<ERS_Reimbersement> reimbursements = new ArrayList<ERS_Reimbersement>();

		System.out.println(user.getUser_roleId().getUser_role());
		if (user.getUser_roleId().getUser_role().equals("F Manager")) {

			System.out.println("manager Query");

			reimbursements = (List<ERS_Reimbersement>) session
					.createQuery("FROM ERS_Reimbersement", ERS_Reimbersement.class).getResultList();

		} else if (user.getUser_roleId().getUser_role().equals("Employee")) {

			System.out.println("Employee Query");
			reimbursements = (List<ERS_Reimbersement>) session
					.createQuery("FROM ERS_Reimbersement WHERE reimb_author = :author", ERS_Reimbersement.class)
					.setParameter("author", user).getResultList();
		}

		System.out.println(reimbursements);
		
		if(reimbursements.size() == 0) {
			System.out.println();
			throw new LoginException("no Reimbursement found.");
		}
		
		
		for(ERS_Reimbersement reimbursement : reimbursements) {
			String author = reimbursement.getReimb_author().getUserFirstName() + " " + reimbursement.getReimb_author().getUserLastName();
			String resolver = null;
			byte[] receipt = null;
			
			if(reimbursement.getReimb_receipt() != null) {
				receipt = reimbursement.getReimb_receipt();
			}
			
			if(reimbursement.getReimb_resolver() != null) {
				resolver = reimbursement.getReimb_resolver().getUserFirstName() + "  " + reimbursement.getReimb_resolver().getUserLastName();
			}
			
			CleanReimbursement display = new CleanReimbursement(reimbursement.getReimb_id(), reimbursement.getReimb_amount(), reimbursement.getReimb_submitted(), 
					reimbursement.getReimb_resolved(), reimbursement.getReimb_description(), receipt, author, resolver, reimbursement.getReimb_status_id().getReimb_status(),
					reimbursement.getReimb_type_id().getReimb_type());
			cleanreimbursements.add(display);
			
		}
		
		return cleanreimbursements;
		}catch(NoResultException e) {
			throw new LoginException("No reimbursement found.");
		}
	}

	public ERS_Reimbersement addReimbursment(ERS_Users user, ERS_ReimbursementDTO reimbursementDTO) {
		Session session = SessionUtility.getSession();

		System.out.println(reimbursementDTO.getReimb_amount() + " " + reimbursementDTO.getReimb_description()
				+ reimbursementDTO.getReimType());
		
		ERS_Reimbursement_Status reimStatus = session.get(ERS_Reimbursement_Status.class, 1);

		ERS_Reimbursement_Type reimType = (ERS_Reimbursement_Type) session
				.createQuery("FROM ERS_Reimbursement_Type WHERE reimb_type = :type")
				.setParameter("type", reimbursementDTO.getReimType()).getSingleResult();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Transaction tx = session.beginTransaction();
		
		ERS_Reimbersement reimbursement = new ERS_Reimbersement(reimbursementDTO.getReimb_amount(), timestamp, null,
				reimbursementDTO.getReimb_description(), reimbursementDTO.getReimb_receipt(), user, null, reimStatus,
				reimType);

		session.save(reimbursement);
		tx.commit();
		return reimbursement;
	}

	public ERS_Users approveReimbursement(ERS_Users user, String reimID)
			throws DatabaseException, NoResultException, AuthenticationException, BadParameterException, LoginException {
		try (Session session = SessionUtility.getSession()) {

			if (user.getUser_roleId().getUser_role().equals("Employee")) {
				throw new AuthenticationException("Only Manager are allowed resolve Reimbursement");
			}

			Transaction tx = session.beginTransaction();
			Timestamp now = new Timestamp(System.currentTimeMillis());

			ERS_Reimbursement_Status approve = (ERS_Reimbursement_Status) session
					.createQuery("FROM ERS_Reimbursement_Status WHERE reimb_status_id = :status")
					.setParameter("status", 2).getSingleResult();

			int reimid = Integer.parseInt(reimID);

			ERS_Reimbersement approveReimbursement = session.get(ERS_Reimbersement.class, reimid);

			if (!approveReimbursement.getReimb_status_id().getReimb_status().equals("Pending")) {
				throw new BadParameterException("Only Pending reimbursement can be approved.");
			}

			approveReimbursement.setReimb_resolver(user);
			approveReimbursement.setReimb_status_id(approve);
			approveReimbursement.setReimb_resolved(now);

			session.save(approveReimbursement);
			tx.commit();

			return user;
		}catch(NoResultException e) {
			throw new LoginException("Unable to Approve Reimbursement.");
		}

	}

	public ERS_Users denieReimbursement(ERS_Users user, String reimID) throws AuthenticationException, BadParameterException, LoginException {
		try (Session session = SessionUtility.getSession()) {

			if (user.getUser_roleId().getUser_role().equals("Employee")) {
				throw new AuthenticationException("Only Manager are allowed resolve Reimbursement");
			}

			Transaction tx = session.beginTransaction();
			Timestamp now = new Timestamp(System.currentTimeMillis());

			ERS_Reimbursement_Status approve = (ERS_Reimbursement_Status) session
					.createQuery("FROM ERS_Reimbursement_Status WHERE reimb_status_id = :status")
					.setParameter("status", 3).getSingleResult();

			int reimid = Integer.parseInt(reimID);

			ERS_Reimbersement approveReimbursement = session.get(ERS_Reimbersement.class, reimid);

			if (!approveReimbursement.getReimb_status_id().getReimb_status().equals("Pending")) {
				throw new BadParameterException("Only Pending reimbursement can be Denied.");
			}

			approveReimbursement.setReimb_resolver(user);
			approveReimbursement.setReimb_status_id(approve);
			approveReimbursement.setReimb_resolved(now);

			session.save(approveReimbursement);
			tx.commit();

			return user;
		}catch(NoResultException e) {
			throw new LoginException("Unable to Approve Reimbursement.");
		}
	}

}

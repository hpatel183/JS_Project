package com.reimbursement.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.reimbursement.dto.ERS_ReimbursementDTO;
import com.reimbursement.exception.AuthenticationException;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.exception.DatabaseException;
import com.reimbursement.exception.NoResultException;
import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_Reimbursement_Status;
import com.reimbursement.models.ERS_Reimbursement_Type;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.utils.SessionUtility;

public class ERS_ReimbursementDAO {

	public List<ERS_Reimbersement> getAllreimbursement(ERS_Users user) {

		System.out.println("Get Session");
		Session session = SessionUtility.getSession();
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
		return reimbursements;
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
		return null;
	}

	public ERS_Users approveReimbursement(ERS_Users user, String reimID)
			throws DatabaseException, NoResultException, AuthenticationException, BadParameterException {
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
		}

	}

	public ERS_Users denieReimbursement(ERS_Users user, String reimID) throws AuthenticationException, BadParameterException {
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
		}
	}

}

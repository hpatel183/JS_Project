package com.reimbursement.main;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_Reimbursement_Status;
import com.reimbursement.models.ERS_Reimbursement_Type;
import com.reimbursement.models.ERS_User_Roles;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.utils.SessionUtility;

public class Test {
	
	public static void main(String[] args) {
		
		Session session = SessionUtility.getSession();	//create table
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		Transaction tx = session.beginTransaction();
		
		
		
		
		
		
		tx.commit();
		
		
		
		/*
		 * 
		 * Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //
		 * reimb.setSubmittedTime(timestamp);
		 * 
		 * ERS_Users author = session.get(ERS_Users.class, 1); ERS_Users resolver =
		 * session.get(ERS_Users.class, 4);
		 * 
		 * 
		 * ERS_Reimbursement_Status pending =
		 * session.get(ERS_Reimbursement_Status.class, 1); ERS_Reimbursement_Status
		 * approved = session.get(ERS_Reimbursement_Status.class, 2);
		 * ERS_Reimbursement_Status denied = session.get(ERS_Reimbursement_Status.class,
		 * 3);
		 * 
		 * ERS_Reimbursement_Type lodging = session.get(ERS_Reimbursement_Type.class,
		 * 1); ERS_Reimbursement_Type travel = session.get(ERS_Reimbursement_Type.class,
		 * 2); ERS_Reimbursement_Type food = session.get(ERS_Reimbursement_Type.class,
		 * 3); ERS_Reimbursement_Type other = session.get(ERS_Reimbursement_Type.class,
		 * 4);
		 * 
		 * ERS_Reimbersement reim1 = new ERS_Reimbersement(100, timestamp, timestamp,
		 * "Client meeting", author, resolver, pending, lodging); ERS_Reimbersement
		 * reim2 = new ERS_Reimbersement(100, timestamp, timestamp, "Client meeting",
		 * author, resolver, approved, travel); ERS_Reimbersement reim3 = new
		 * ERS_Reimbersement(100, timestamp, timestamp, "Client meeting", author,
		 * resolver, approved, food); ERS_Reimbersement reim4 = new
		 * ERS_Reimbersement(100, timestamp, timestamp, "Client meeting", author,
		 * resolver, denied, travel); ERS_Reimbersement reim5 = new
		 * ERS_Reimbersement(100, timestamp, timestamp, "Client meeting", author,
		 * resolver, pending, other);
		 * 
		 * session.save(reim1); session.save(reim2); session.save(reim3);
		 * session.save(reim4); session.save(reim5);
		 * 
		 * reim1.setReimb_author(author); reim1.setReimb_resolver(resolver);
		 * reim1.setReimb_status_id(pending); reim1.setReimb_type_id(lodging);
		 * reim2.setReimb_author(author); reim2.setReimb_resolver(resolver);
		 * reim2.setReimb_status_id(approved); reim2.setReimb_type_id(travel);
		 * reim3.setReimb_author(author); reim3.setReimb_resolver(resolver);
		 * reim3.setReimb_status_id(approved); reim3.setReimb_type_id(food);
		 * reim4.setReimb_author(author); reim4.setReimb_resolver(resolver);
		 * reim4.setReimb_status_id(denied); reim4.setReimb_type_id(travel);
		 * reim5.setReimb_author(author); reim5.setReimb_resolver(resolver);
		 * reim5.setReimb_status_id(pending); reim5.setReimb_type_id(other);
		 * 
		 * System.out.println(reim1); System.out.println(reim2);
		 * System.out.println(reim3); System.out.println(reim4);
		 * System.out.println(reim5);
		 * 
		 * 
		 */
		
		
				//ERS_User_Roles empRole = new ERS_User_Roles("Employee");
				//ERS_User_Roles manRole = new ERS_User_Roles("F Manager");
				
				//session.save(manRole);
				//session.save(empRole);
				
				//ERS_User_Roles empRoleAssign = session.get(ERS_User_Roles.class, 1);
				//ERS_User_Roles manRoleAssign = session.get(ERS_User_Roles.class, 2);
		
				//ERS_Reimbursement_Type empRole = new ERS_Reimbursement_Type("Lodging");
				//ERS_Reimbursement_Type empRole = new ERS_Reimbursement_Type("Travel");
				//ERS_Reimbursement_Type empRole = new ERS_Reimbursement_Type("Food");
				//ERS_Reimbursement_Type empRole = new ERS_Reimbursement_Type("Other");
				
				//ERS_Reimbursement_Status empRole = new ERS_Reimbursement_Status("Pending");
				//ERS_Reimbursement_Status empRole = new ERS_Reimbursement_Status("Approved");
				//ERS_Reimbursement_Status empRole = new ERS_Reimbursement_Status("Denied");
				
				/*
				 * ERS_Users user1 = new ERS_Users("usename1", "password1", "FirstName1",
				 * "LastName1", "user@hotmail.com", empRole); ERS_Users user2 = new
				 * ERS_Users("manager1", "password2", "FirstName2", "LastName2",
				 * "manager1@hotmail.com", manRole); ERS_Users user3 = new ERS_Users("usename3",
				 * "password3", "FirstName3", "LastName3", "user3@hotmail.com", manRole);
				 * ERS_Users user4 = new ERS_Users("usename4", "password4", "FirstName4",
				 * "LastName4", "user4@hotmail.com", empRole); ERS_Users user5 = new
				 * ERS_Users("usename5", "password5", "FirstName5", "LastName5",
				 * "user5@hotmail.com", empRole);
				 * 
				 * //ERS_Users user6 = new ERS_Users("usename", "password6", "FirstName6",
				 * "LastName6", "user6@hotmail.com", empRole); //Unique works
				 * 
				 * session.save(user1); session.save(user2); session.save(user3);
				 * session.save(user4); session.save(user5);
				 * 
				 * user1.setUser_roleId(empRoleAssign); user2.setUser_roleId(manRoleAssign);
				 * user3.setUser_roleId(manRoleAssign); user4.setUser_roleId(empRoleAssign);
				 * user5.setUser_roleId(empRoleAssign);
				 */		
		
		/*
		 * Timestamp submitted = new Timestamp (100000000); Timestamp resolved = new
		 * Timestamp (200000000);
		 * 
		 * ErsUsers userJake = session.get(ErsUsers.class, 1); ErsUsers userEmma =
		 * session.get(ErsUsers.class, 2); ErsUsers userCristine = session.get
		 * (ErsUsers.class, 3); ErsUsers userJimmy = session.get(ErsUsers.class, 4);
		 * ErsUsers userJenna = session.get(ErsUsers.class, 5);
		 * 
		 * ErsUsers managerSusan = session.get (ErsUsers.class, 6); ErsUsers
		 * managerRobert = session.get(ErsUsers.class, 7); ErsUsers managerDanielle =
		 * session.get(ErsUsers.class, 8);|| ErsUsers managerNeal =
		 * session.get(ErsUsers.class, 9); ErsUsers managerPawan =
		 * session.get(ErsUsers.class, 10);
		 * 
		 * ERS_Reimbursement_Status statusPending =
		 * session.get(ERS_Reimbursement_Status.class, 1); ERS_Reimbursement_Status
		 * statusApproved = session.get(ERS_Reimbursement_Status.class, 2);
		 * ERS_Reimbursement_Status statusDenied=
		 * session.get(ERS_Reimbursement_Status.class, 3);
		 * 
		 * ERS_Reimbursement_Type typeLodging =
		 * session.get(ERS_Reimbursement_Type.class, 1); ERS_Reimbursement_Type
		 * typeTravel = session.get(ERS_Reimbursement_Type.class, 2);
		 * ERS_Reimbursement_Type typeFood = session.get(ERS_Reimbursement_Type.class,
		 * 3); ERS_Reimbursement_Type typeOther =
		 * session.get(ERS_Reimbursement_Type.class, 4);
		 */
		
		//(100, "", "", "Client meeting", "receipt", 
		

		/*
		 * ERS_User_Roles empRoleAssign = session.get(ERS_User_Roles.class, 1);
		 * ERS_User_Roles manRoleAssign = session.get(ERS_User_Roles.class, 2);
		 * 
		 * ERS_Users userJake = new ERS_Users("iakie", "paulsome", "Jake", "Paul","iakepaul@youtube.com", empRoleAssign); 
		 * ERS_Users userEmma = new
		 * ERS_Users("emmachambie", "coffeeisgood", "Emma", "Chamberlain",
		 * "emmachamberlainayoutube.com", empRoleAssign); 
		 * ERS_Users userCristine = new
		 * ERS_Users ("simply", "beyn", "Cristine", "Rotenburg",
		 * "simplynailogicalayoutube.com", empRoleAssign); ERS_Users userJimmy = new
		 * ERS_Users("mrbeast", "moneyman", "Jimmy", "Donaldson", "mrbeastayoutube.com",
		 * empRoleAssign); ERS_Users userJenna = new ERS_Users("marbles", "hellyeah",
		 * "Jenna", "purey", "iennamarbles@youtube.com", manRoleAssign);
		 * 
		 * 
		 * session.save(userJake); session.save(userEmma); session.save(userCristine);
		 * session.save(userJimmy); session.save(userJenna);
		 * 
		 * userJake.setUser_roleId(empRoleAssign);
		 * userEmma.setUser_roleId(empRoleAssign);
		 * userCristine.setUser_roleId(empRoleAssign);
		 * userJimmy.setUser_roleId(empRoleAssign);
		 * userJenna.setUser_roleId(manRoleAssign);
		 */
	}
}

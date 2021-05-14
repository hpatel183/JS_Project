package com.reimbursement.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockedStatic;

import com.reimbursement.dao.ERS_ReimbursementDAO;
import com.reimbursement.dto.CleanReimbursement;
import com.reimbursement.dto.ERS_ReimbursementDTO;
import com.reimbursement.exception.LoginException;
import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_User_Roles;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.service.ERS_ReimbursementService;
import com.reimbursement.utils.SessionUtility;

public class ReimbursementTest {

	private static ERS_ReimbursementDAO mockReimbersementDAOTest;
	private static Session mockSessionFactory;
	
	public ERS_ReimbursementService reimbursementService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws LoginException {
		mockReimbersementDAOTest = mock(ERS_ReimbursementDAO.class);
		mockSessionFactory = mock(Session.class);
		
		ERS_Users testEmployee = new ERS_Users("usename", "password", "user1@hotmail.com", "FirstName", "LastName", new ERS_User_Roles("Employee"));
		List<CleanReimbursement> reimbursements = new ArrayList<>();
		
		CleanReimbursement reim1 = new CleanReimbursement(1, 100, new Date(010121), new Date(010221), "Client Meeting", null, "Author", null, "Pending", "Food");
		CleanReimbursement reim2 = new CleanReimbursement(2, 200, new Date(010121), new Date(010221), "Manager Meeting", null, "Author", "F Manager", "Approved", "Travel");
		reimbursements.add(reim1);
		reimbursements.add(reim2);
		
		when(mockReimbersementDAOTest.getAllreimbursement(eq(testEmployee))).thenReturn(reimbursements);
		
		
		
		/*
		 * ERS_Reimbersement reim = new ERS_Reimbersement(1, 100, new Timestamp(010121),
		 * new Timestamp(010121), "Client Meeting", null, "Author", "", "Pending",
		 * "Food"); ERS_ReimbursementDTO reimbursementDTO = new
		 * ERS_ReimbursementDTO(100, "Client Meeting", null, "Food");
		 * when(mockReimbersementDAOTest.addReimbursment(eq(testEmployee),
		 * eq(reimbursementDTO))).thenReturn(reim);
		 */
		
	}
	

	@Before
	public void beforeTest() {
		System.out.println("In before test");
		reimbursementService = new ERS_ReimbursementService(mockReimbersementDAOTest);
	}
	
	@Test 
	public void test_getAllReimbursements() throws LoginException {
		try(MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
			mockedSessionUtil.when(SessionUtility::getSession).thenReturn(mockSessionFactory);
			System.out.println("In testallreim");
			
			List<CleanReimbursement> actual = reimbursementService.getAllreimbursement(new ERS_Users("usename", "password", "user1@hotmail.com", "FirstName", "LastName", new ERS_User_Roles("Employee")));
					
			List<CleanReimbursement> expected = new ArrayList<>();
			CleanReimbursement reim1 = new CleanReimbursement(1, 100, new Date(010121), new Date(010221), "Client Meeting", null, "Author", null, "Pending", "Food");
			CleanReimbursement reim2 = new CleanReimbursement(2, 200, new Date(010121), new Date(010221), "Manager Meeting", null, "Author", "F Manager", "Approved", "Travel");
			expected.add(reim1);
			expected.add(reim2);
			
			assertEquals(expected, actual);
		}
	}
	
}

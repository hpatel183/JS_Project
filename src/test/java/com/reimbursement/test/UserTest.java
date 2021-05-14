package com.reimbursement.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import static org.mockito.ArgumentMatchers.eq;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mockito.MockedStatic;

import com.reimbursement.dao.ERS_UsersDAO;
import com.reimbursement.dto.ERS_UsersDTO;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.exception.LoginException;
import com.reimbursement.models.ERS_User_Roles;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.service.ERS_UserService;
import com.reimbursement.utils.SessionUtility;

public class UserTest {
	private static ERS_UsersDAO mockUserDAOTest;
	private static Session mockSessionFactory;
	
	public ERS_UserService userService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mockUserDAOTest = mock(ERS_UsersDAO.class);
		mockSessionFactory = mock(Session.class);
		
		ERS_Users testEmployee = new ERS_Users("usename", "password", "user1@hotmail.com", "FirstName", "LastName", new ERS_User_Roles("Employee"));
		
		when(mockUserDAOTest.getUserByUsernameAndPassword(eq(new ERS_UsersDTO("usename", "password")))).thenReturn(testEmployee);
		
		when(mockUserDAOTest.addUserInfo(eq(new ERS_UsersDTO("usename", "password", "user1@hotmail.com", "FirstName", "LastName", "Employee"))))
				.thenReturn(testEmployee);
	}

	@Before
	public void setUpBeforeTest() {
		System.out.println("In before test");
		userService = new ERS_UserService(mockUserDAOTest);
	}

	@Test
	public void testLogin() throws BadParameterException, LoginException {
		try(MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
			mockedSessionUtil.when(SessionUtility::getSession).thenReturn(mockSessionFactory);
			System.out.println("In testlogin");
			ERS_Users actual = userService.login(new ERS_UsersDTO("usename", "password"));
			ERS_Users expected = new ERS_Users("usename", "password", "user1@hotmail.com", "FirstName", "LastName", new ERS_User_Roles("Employee"));
			
			assertEquals(expected, actual);
		}
	}
	
	@Test
	public void testAddUser() throws BadParameterException, LoginException, SecurityException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		try(MockedStatic<SessionUtility> mockedSessionUtil = mockStatic(SessionUtility.class)) {
			mockedSessionUtil.when(SessionUtility::getSession).thenReturn(mockSessionFactory);
			
			System.out.println("In testAdduser");
			ERS_Users actual = userService.addUser(new ERS_UsersDTO("usename", "password", "user1@hotmail.com", "FirstName", "LastName", "Employee"));
			ERS_Users expected = new ERS_Users("usename", "password", "user1@hotmail.com", "FirstName", "LastName", new ERS_User_Roles("Employee"));
			
			assertEquals(expected, actual);
		}
	}

}

package com.reimbursement.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.reimbursement.dao.ERS_UsersDAO;
import com.reimbursement.dto.ERS_UsersDTO;
import com.reimbursement.service.ERS_UserService;

class UserTest {
	private static ERS_UsersDAO mockUserTest;
	public ERS_UserService userService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockUserTest = mock(ERS_UsersDAO.class);
		
	//	when(mockUserTest.getUserByUsernameAndPassword(eq(new ERS_UsersDTO("usename", "password", "user1@hotmail.com", "FirstName", "LastName", "Employee" ))))
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}

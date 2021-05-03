package com.reimbursement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reimbursement.dto.MessageDTO;
import com.reimbursement.exception.AuthenticationException;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.exception.DatabaseException;
import com.reimbursement.exception.LoginException;
import com.reimbursement.exception.NoResultException;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

public class ExceptionController implements Controller{
	
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	private ExceptionHandler<BadParameterException> badParameterExceptionHandler = (e, ctx) -> {
		logger.warn("User provided bad parameter. \nException msg is: " + e.getMessage());
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<LoginException> loginExceptionHandler = (e, ctx) -> {
		logger.warn("Username and Password NOT Found. \nException msg is: " + e.getMessage());
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<NoResultException> noresultExceptionHandler = (e, ctx) -> {
		logger.warn("LogIn Issue Found. \nException msg is: " + e.getMessage());
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<DatabaseException> databaseExceptionHandler = (e, ctx) -> {
		logger.warn("Database Connection Issue Found. \nException msg is: " + e.getMessage());
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	private ExceptionHandler<AuthenticationException> authenticationExceptionHandler = (e, ctx) -> {
		logger.warn("Authentication Issue found. \nException msg is: " + e.getMessage());
		ctx.status(400);
		ctx.json(new MessageDTO(e.getMessage()));
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.exception(BadParameterException.class, badParameterExceptionHandler);
		app.exception(LoginException.class, loginExceptionHandler);
		app.exception(NoResultException.class, noresultExceptionHandler);
		app.exception(DatabaseException.class, databaseExceptionHandler);
		app.exception(AuthenticationException.class, authenticationExceptionHandler);
		
		
	}
}

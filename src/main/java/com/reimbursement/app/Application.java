package com.reimbursement.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reimbursement.controller.Controller;
import com.reimbursement.controller.ERS_ReimbursementController;
import com.reimbursement.controller.ERS_UsersController;
import com.reimbursement.controller.ExceptionController;
import com.reimbursement.controller.StaticFileController;

import io.javalin.Javalin;


public class Application {
	

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		
		Javalin app = Javalin.create((config) -> {
			config.addStaticFiles("static");
			config.enableCorsForAllOrigins();
//			config.enableCorsForOrigin("http://somewebsite.com");
		});
		
		app.before(ctx -> {
			String URI = ctx.req.getRequestURI();
			String httpMethod = ctx.req.getMethod();
			logger.info(httpMethod + " request to endpoint " + URI + " received");
		});		
		
		mapControllers(app, new ERS_UsersController(), new ExceptionController(), new ERS_ReimbursementController(), new StaticFileController()); 
	
		app.start(7000);
	}
	
	private static void mapControllers(Javalin app, Controller... controllers) {
		for (Controller c : controllers) {
			c.mapEndpoints(app);
		}
	}

}

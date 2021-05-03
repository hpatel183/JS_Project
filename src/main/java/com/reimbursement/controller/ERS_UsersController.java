package com.reimbursement.controller;

import java.util.ArrayList;

import com.reimbursement.dto.ERS_UsersDTO;
import com.reimbursement.dto.MessageDTO;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.service.ERS_UserService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ERS_UsersController implements Controller {

	private ERS_UserService userService;

	public ERS_UsersController() {
		this.userService = new ERS_UserService();
	}

	private Handler loginHandler = (ctx) -> {
		ERS_UsersDTO userDTO = ctx.bodyAsClass(ERS_UsersDTO.class);

		System.out.println("Go to service");
		ERS_Users user = userService.login(userDTO);

		ctx.sessionAttribute("currentlyLoggedInUser", user);
		ctx.json(user);
		ctx.status(200);
	};

	private Handler currentUserHandler = (ctx) -> {

		System.out.println("CurrentUser");
		ERS_Users user = (ERS_Users) ctx.sessionAttribute("currentlyLoggedInUser");

		if (user == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not currently logged in!");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
			System.out.println(user);
			ctx.status(201);
			ctx.json(user);
		}
	};

	private Handler logoutHandler = (ctx) -> {

		ctx.req.getSession().invalidate();
		System.out.println("Logout");
		ctx.json("Log Out");
	};

	private Handler addUserHandler = (ctx) -> {
		System.out.println("Create add user handler");
		ERS_UsersDTO userDTO = ctx.bodyAsClass(ERS_UsersDTO.class);

		System.out.println("Go to add service");
		ERS_Users user = userService.addUser(userDTO);

		ctx.status(201);
		ctx.json(user);
		System.out.println("Add user complete.");
	};

	private Handler getAllUserhandler = (ctx) -> {

		System.out.println("All Employee");

		ERS_Users user = (ERS_Users) ctx.sessionAttribute("currentlyLoggedInUser");

		if (user == null) {
			MessageDTO messageDTO = new MessageDTO();
			messageDTO.setMessage("User is not currently logged in!");
			ctx.json(messageDTO);
			ctx.status(400);
		} else {
			System.out.println(user);
			ctx.status(201);
			ctx.json(user);
		}

		// ERS_UsersDTO userDTO = ctx.bodyAsClass(ERS_UsersDTO.class);

		ArrayList<ERS_Users> users = userService.getAllusers();

		ctx.status(200);
		ctx.json(users);
		System.out.println("All user display.");
	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.post("/login", loginHandler);
		app.get("/current_user", currentUserHandler);
		app.post("/logout", logoutHandler);
		app.post("/addUser", addUserHandler);
		app.get("/getalluser", getAllUserhandler);
	}
}

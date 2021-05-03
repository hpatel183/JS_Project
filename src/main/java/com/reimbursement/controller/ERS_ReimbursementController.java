package com.reimbursement.controller;

import java.util.List;

import com.reimbursement.dto.ERS_ReimbursementDTO;
import com.reimbursement.dto.MessageDTO;
import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.service.ERS_ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ERS_ReimbursementController implements Controller {

	private ERS_ReimbursementService reimbursementService;

	public ERS_ReimbursementController() {
		this.reimbursementService = new ERS_ReimbursementService();
	}

	private Handler getAllReimHandler = (ctx) -> {

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

		System.out.println("Go to service");
		List<ERS_Reimbersement> reimbursements = reimbursementService.getAllreimbursement(user);

		ctx.status(200);
		ctx.json(reimbursements);
		System.out.println("All reimbursements display.");
	};

	private Handler addReimHandler = (ctx) -> {

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

		ERS_ReimbursementDTO reimbursementDTO = ctx.bodyAsClass(ERS_ReimbursementDTO.class);

		System.out.println("Go to add reimbursement service");

		ERS_Reimbersement reimbursement = reimbursementService.addreimbursement(user, reimbursementDTO);

		ctx.status(201);
		// ctx.json(reimbursement);
		System.out.println("Add reimbursement complete.");
	};

	private Handler approvedReimHandler = (ctx) -> {
		ERS_Users user = (ERS_Users) ctx.sessionAttribute("currentlyLoggedInUser");

		String reimID = ctx.pathParam("id");
		
		System.out.println("Start Approved Reimbursement");
		ERS_Users reimResolverApprove = reimbursementService.approveReimbursement(user, reimID);

		System.out.println("Approved Reimbursement");
		ctx.json(reimResolverApprove);
		ctx.status(201);

	};

	private Handler deniedReimHandler = (ctx) -> {
		
		ERS_Users user = (ERS_Users) ctx.sessionAttribute("currentlyLoggedInUser");

		String reimID = ctx.pathParam("id");
		System.out.println("Start Denied Reimbursement");
		ERS_Users reimResolverDeny = reimbursementService.denyReimbursement(user, reimID);

		System.out.println("Denied Reimbursement");
		ctx.json(reimResolverDeny);
		ctx.status(201);

	};

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/getreimbursements", getAllReimHandler);
		app.post("/addreimbursement", addReimHandler);
		app.post("/reimbursement/:id/approveReimbursement", approvedReimHandler);
		app.post("/reimbursement/:id/deniedReimbursement", deniedReimHandler);
	}

}

package com.reimbursement.controller;

import java.io.InputStream;
import java.util.List;

import com.reimbursement.dto.CleanReimbursement;
import com.reimbursement.dto.ERS_ReimbursementDTO;
import com.reimbursement.dto.MessageDTO;
import com.reimbursement.exception.BadParameterException;
import com.reimbursement.models.ERS_Reimbersement;
import com.reimbursement.models.ERS_Users;
import com.reimbursement.service.ERS_ReimbursementService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

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
			ctx.status(200);
			ctx.json(user);
		}

		System.out.println("Go to service");
		List<CleanReimbursement> reimbursements = reimbursementService.getAllreimbursement(user);

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
		}
		/*
		 * else { System.out.println(user); ctx.status(201); ctx.json(user); }
		 */

		ctx.contentType("multipart/form-data");

		if (ctx.formParam("reimb_amount").equals("") || ctx.formParam("reimb_description").equals("")
				|| ctx.formParam("reimType").equals("")) {
			throw new BadParameterException("Receipt is Optional.");
		}

		System.out.println("amount " + ctx.formParam("reimb_amount"));
		System.out.println("desc " + ctx.formParam("reimb_description"));
		System.out.println("reimType " + ctx.formParam("reimType"));

		ERS_ReimbursementDTO reimDTO = new ERS_ReimbursementDTO();
		reimDTO.setReimb_amount(Integer.parseInt(ctx.formParam("reimb_amount")));
		reimDTO.setReimb_description(ctx.formParam("reimb_description"));
		reimDTO.setReimType(ctx.formParam("reimType"));

		if (ctx.uploadedFiles().size() != 0) {
			UploadedFile upfile = ctx.uploadedFiles().get(0);
			InputStream bytes = upfile.component1();
			byte[] byteArray = bytes.readAllBytes();
			reimDTO.setReimb_receipt(byteArray);
		} else {
			reimDTO.setReimb_receipt(null);
		}

		ERS_Reimbersement reimbursement = reimbursementService.addreimbursement(user, reimDTO);

		System.out.println(reimbursement);
		ctx.status(201);
		ctx.json(reimbursement);
		System.out.println("Add reimbursement complete.");
	};

	private Handler approvedReimHandler = (ctx) -> {
		ERS_Users user = (ERS_Users) ctx.sessionAttribute("currentlyLoggedInUser");

		String reimID = ctx.pathParam("id");

		System.out.println("Start Approved Reimbursement");
		ERS_Users reimResolverApprove = reimbursementService.approveReimbursement(user, reimID);

		System.out.println("Approved Reimbursement");
		ctx.json(reimResolverApprove);
		ctx.status(200);

	};

	private Handler deniedReimHandler = (ctx) -> {

		ERS_Users user = (ERS_Users) ctx.sessionAttribute("currentlyLoggedInUser");

		String reimID = ctx.pathParam("id");
		System.out.println("Start Denied Reimbursement");
		ERS_Users reimResolverDeny = reimbursementService.denyReimbursement(user, reimID);

		System.out.println("Denied Reimbursement");
		ctx.json(reimResolverDeny);
		ctx.status(200);

	};

	/*
	 * app.patch(/reimbursement/id) ?action=deny ?action=approve if statements for
	 * the query param action
	 */

	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/getreimbursements", getAllReimHandler);
		app.post("/addreimbursement", addReimHandler);
		app.post("/reimbursement/:id/approveReimbursement", approvedReimHandler);
		app.post("/reimbursement/:id/deniedReimbursement", deniedReimHandler);
	}

}

package com.redhat.emea.example.bfod;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/restservice/")
public class PaymentRESTService {

	public PaymentRESTService() {

	}

	@GET
	@Path("/payment/{input}/")
	@Produces("text/plain")
	public String getSomething(@PathParam("input") String input) {
		System.out.println("Called the frontend Service");
		return "Submitted";
	}

}

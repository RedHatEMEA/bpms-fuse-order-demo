package com.redhat.emea.example.bfod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/restservice/")
public class PaymentRESTService
{

	public PaymentRESTService()
	{

	}

	@GET
	@Path("/payment/{input}/")
	@Produces("text/plain")
	public String getSomething(@PathParam("input") String input)
	{
		System.out.println("Called the frontend Service");
		System.out.println(input);
		return "Submitted";
	}

	@POST
	@Path("/payment")
	@Consumes(MediaType.APPLICATION_JSON)
	public String postSomething(InputStream input)
	{
		StringBuilder stringBuilder = new StringBuilder();
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			String line = null;
			while ((line = in.readLine()) != null)
			{
				stringBuilder.append(line);
			}
		}
		catch (Exception e)
		{
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + stringBuilder.toString());

		return stringBuilder.toString();
	}

}

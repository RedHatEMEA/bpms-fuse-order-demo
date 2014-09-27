package com.redhat.emea.bfod.proxy.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.emea.example.bfod.model.Order;

@Path("/orderservice/")
public class OrderService
{
	public OrderService()
	{

	}
	

	@POST
	@Path("/order/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response placeOrder(final Order order) throws Exception
	{
		//Hook into BPM with Order found in body
		//Validation is currently occurring as part of the process	
		System.out.println(order);
		
		//Client client = ClientBuilder.newClient();
		//Entity.json(PlaceOrderResponse)
			
		return Response.ok().entity(new PlaceOrderResponse("fromBPM", null)).build();
		
	}
	
	
}

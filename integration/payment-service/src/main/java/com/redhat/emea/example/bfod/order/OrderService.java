package com.redhat.emea.example.bfod.order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.emea.example.bfod.model.Order;

@Path("/orderservice/")
public class OrderService
{
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	public OrderService()
	{

	}
	

	@POST
	@Path("/order")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response placeOrder(final Order order)
	{			
		//Hook into BPM with Order found in body
		//Validation is currently occurring as part of the process	
		System.out.println(order);
		return Response.accepted().entity(new PlaceOrderResponse("fromBPM", null)).build();
		
	}
}

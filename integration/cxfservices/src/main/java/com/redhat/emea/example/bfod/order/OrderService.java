package com.redhat.emea.example.bfod.order;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;

import com.redhat.emea.example.bfod.model.Order;

@Path("/orderservice/")
public class OrderService
{
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	
    
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
			
		return Response.accepted().entity(new PlaceOrderResponse("fromBPM", null)).build();
		
	}
	
	
}

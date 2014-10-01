package com.redhat.emea.bfod.proxy.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.emea.example.bfod.model.Order;

@Path("/orderservice/")
public class OrderService {
	public OrderService() {

	}

	@POST
	@Path("/order/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response placeOrder(final Order order) throws Exception {
		// Hook into BPM with Order found in body
		Client client = ClientBuilder.newClient();

		WebTarget myResource = client
				.target("http://localhost:9191/route/orderservice/order");

		/*PlaceOrderResponse response = myResource.request(
				MediaType.APPLICATION_JSON).post(Entity.json(order),
				PlaceOrderResponse.class);*/
		

		//return Response.ok().entity(response).build();
		Response response = myResource.request().post(Entity.json(order));
				
		
		return response;
	}

}

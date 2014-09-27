package com.redhat.emea.bfod.proxy.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ApplicationConfig extends Application {
	private Set<Object> singletons = new HashSet<Object>();

	  public ApplicationConfig() {
	    singletons.add(new OrderService());
	  }

	  @Override
	  public Set<Object> getSingletons() {
	    return singletons;
	  }
}

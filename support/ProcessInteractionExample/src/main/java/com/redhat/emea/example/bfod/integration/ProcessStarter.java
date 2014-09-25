package com.redhat.emea.example.bfod.integration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.services.client.api.RemoteRestRuntimeFactory;

import com.redhat.emea.example.bfod.model.Order;
import com.redhat.emea.example.bfod.model.Profile;

public class ProcessStarter
{
	public static void main(String args[]) throws MalformedURLException
	{

		ProcessStarter starter = new ProcessStarter();
		long procid = starter.startProcess();
		
		System.out.println("Proccess ID : " + procid);
	}
	
	public long startProcess() throws MalformedURLException
	{
		String deploymentId = "com.redhat.emea.example.bfod:bfod-bpm:1.0.0"; // this can be viewed from business-central -> properties of deployed Process
		URL baseUrl = new URL("http://192.168.33.10:8080/business-central/");
		String user = "bpmadmin";
		String password = "bpmsuite1!";

		RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(deploymentId, baseUrl, user,
				password);

		RuntimeEngine engine = restSessionFactory.newRuntimeEngine();

		KieSession ksession = engine.getKieSession();

		// The process ID is set when creating the process within the designer.
		Map<String, Object> processArgs =getProcessArgs();
		processArgs.put("name", "myname");
		 ProcessInstance processInstance = ksession.startProcess("com.redhat.emea.example.integration-test-process",processArgs );
		
		return processInstance.getId();
		
	
	}

	private static Map<String, Object> getProcessArgs()
	{
		Map<String, Object> processVariables = new HashMap<String, Object>();

		// Create the required arguments to be passed in.

		Profile profile = new Profile();
		profile.setFirstName("A");
		profile.setGender("male");
		profile.setHonorificPrefix("Mr");
		profile.setLastName("D");

		Order order = new Order();

		order.setId("1");
		order.setProfile(profile);
		// order.setCampaign(campaign);
		// order.setCatalogueItems(catalogueItems);
		// The key is set as a variable on the the process.
		processVariables.put("order", order);
		return processVariables;
	}

}

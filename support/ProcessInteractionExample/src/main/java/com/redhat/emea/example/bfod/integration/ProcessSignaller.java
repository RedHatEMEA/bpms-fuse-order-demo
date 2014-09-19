package com.redhat.emea.example.bfod.integration;

import java.net.MalformedURLException;
import java.net.URL;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.api.RemoteRestRuntimeFactory;

public class ProcessSignaller
{
	public static void main(String args[]) throws MalformedURLException
	{

		ProcessSignaller processSignaller = new ProcessSignaller();
		processSignaller.signalProcess(22);
				
		
	}
	
	public void signalProcess(long processId) throws MalformedURLException
	{
		String deploymentId = "com.redhat.emea.example.bfod:bfod-bpm:1.0.0"; // this can be viewed from business-central -> properties of deployed Process
		URL baseUrl = new URL("http://localhost:8080/business-central/");
		String user = "bpmadmin";
		String password = "bpmsuite1!";

		RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(deploymentId, baseUrl, user,
				password);

		RuntimeEngine engine = restSessionFactory.newRuntimeEngine();

		KieSession ksession = engine.getKieSession();

		// The process ID is returned when starting a process
		ksession.signalEvent("complete", true, processId);

		
		
	}


}

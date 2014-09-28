package com.redhat.emea.example.bfod.order;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.services.client.api.RemoteRestRuntimeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.emea.example.bfod.model.Order;

public class OrderProcessor implements Processor
{

	private static final transient Logger LOG = LoggerFactory.getLogger(OrderProcessor.class);

	// this can be viewed from business-central -> properties of deployed
	// Process
	private static final String DEPLOYMENT_ID = "com.redhat.emea.example.bfod:bfod-bpm:1.0.0";
	private static final String BASE_URL = "http://0.0.0.0:8080/business-central/";

	// The process ID is set when creating the process within the designer.
	// "com.redhat.emea.example.integration-test-process"
	private static final String PROCESS_NAME = "com.redhat.emea.example.bfod.order-process";

	public void process(Exchange exchange) throws Exception
	{
		// LOG.info("processing exchange in camel");

		// Get the parameters list which element is the holder.
		// MessageContentsList msgList =
		// (MessageContentsList)exchange.getIn().getBody();
		// String name = "Jon";
		Map<String, Object> parameters = new HashMap<String, Object>();
		// pareters.put("callbackId", callbackId);
		// parameters.put("callbackUri", callbackUri);
		parameters.put("order", (Order) exchange.getIn().getBody());
		// Setup the factory class with the necessarry information to
		// communicate with the REST services

		URL baseUrl = new URL(BASE_URL);
		String user = "bpmadmin";
		String password = "bpmsuite1!";

		RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(DEPLOYMENT_ID, baseUrl, user,
				password);

		RuntimeEngine engine = restSessionFactory.newRuntimeEngine();

		KieSession ksession = engine.getKieSession();
		LOG.info("Got the KieSession");
		// The process ID is set when creating the process within the designer.
		// ProcessInstance processInstance =
		// ksession.startProcess("com.redhat.emea.example.integration-test-process");
		// Use this one when requiring arguments.
		LOG.info("Message body sent to BPM :" + exchange.getIn().getBody().getClass());
		ProcessInstance processInstance = ksession.startProcess(PROCESS_NAME, parameters);
		LOG.info("Started the BPM process with id : " + processInstance);
		// return processInstance.getId();

	}

}

package com.redhat.emea.example.bfod.payment;

import java.net.URL;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.services.client.api.RemoteRestRuntimeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentProcessor implements Processor
{

	private static final transient Logger LOG = LoggerFactory.getLogger(PaymentProcessor.class);

	// this can be viewed from business-central -> properties of deployed
	// Process
	private static final String DEPLOYMENT_ID = "com.redhat.emea.example.bfod:bfod-bpm:1.0.0";
	private static final String BASE_URL = "http://0.0.0.0:8080/business-central/";


	public void process(Exchange exchange) throws Exception
	{

		String[] paymentDetails = ((String) exchange.getIn().getBody()).split(",");

		String personId = paymentDetails[0];
		boolean paymentFailed = false;
		long processId = new Long(paymentDetails[1]).longValue();

		if (personId.equalsIgnoreCase("joebloggs"))
		{
			paymentFailed = true;

		}
		// = exchange.getIn().getBody();

		// Setup the factory class with the necessary information to
		// communicate with the REST services
		
		URL baseUrl = new URL(BASE_URL);
		String user = "bpmadmin";
		String password = "bpmsuite1!";

		RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(DEPLOYMENT_ID, baseUrl, user,
				password);

		RuntimeEngine engine = restSessionFactory.newRuntimeEngine();

		KieSession ksession = engine.getKieSession();
		LOG.info("Got the KieSession");

		LOG.info("Message body sent to BPM :" + exchange.getIn().getBody().getClass());
		ksession.signalEvent("complete", paymentFailed, processId);
		LOG.info("Signalled the BPM process");


	}

}

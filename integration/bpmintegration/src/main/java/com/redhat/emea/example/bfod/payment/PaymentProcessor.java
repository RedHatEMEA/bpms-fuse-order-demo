package com.redhat.emea.example.bfod.payment;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.services.client.api.RemoteRestRuntimeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.emea.example.bfod.model.Order;

//import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.manager.RuntimeEngine;
//import org.kie.services.client.api.RemoteRestRuntimeFactory;
//import java.net.MalformedURLException;
//import java.net.URL;


public class PaymentProcessor implements Processor {
	
	private static final transient Logger LOG = LoggerFactory.getLogger(PaymentProcessor.class);
	 
    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
    	
    	String[] paymentDetails = ((String)exchange.getIn().getBody()).split(",");
    	
    	String personId = paymentDetails[0];
    	boolean paymentFailed = false;
    	long processId = new Long(paymentDetails[1]).longValue();
    	
    	if(personId.equalsIgnoreCase("")){
    		paymentFailed = true;
    		
    	}
    	//= exchange.getIn().getBody();
    	
        // Setup the factory class with the necessarry information to communicate with the REST services
    	String deploymentId = "com.redhat.emea.example.bfod:bfod-bpm:1.0.0"; // this can be viewed from business-central -> properties of deployed Process
		URL baseUrl = new URL("http://0.0.0.0:8080/business-central/");
		String user = "bpmadmin";
		String password = "bpmsuite1!";

		
		RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(deploymentId, baseUrl, user,
				password);

		RuntimeEngine engine = restSessionFactory.newRuntimeEngine();

		KieSession ksession = engine.getKieSession();
		LOG.info("Got the KieSession");
		// The process ID is set when creating the process within the designer.
		//ProcessInstance processInstance = ksession.startProcess("com.redhat.emea.example.integration-test-process");
		// Use this one when requiring arguments.
		LOG.info("Message body sent to BPM :" + exchange.getIn().getBody().getClass());
		ksession.signalEvent("complete", paymentFailed, processId);
		LOG.info("Signalled the BPM process");
		//return processInstance.getId();

    }

}

package com.redhat.emea.example.bfod.payment;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

//import org.kie.api.runtime.KieSession;
//import org.kie.api.runtime.manager.RuntimeEngine;
//import org.kie.services.client.api.RemoteRestRuntimeFactory;
//import java.net.MalformedURLException;
//import java.net.URL;


public class PaymentProcessor implements Processor {
	
	//private static final transient Logger LOG = LoggerFactory.getLogger(PersonProcessor.class);
	 
    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        //LOG.info("processing exchange in camel");
 
        // Get the parameters list which element is the holder.
        //MessageContentsList msgList = (MessageContentsList)exchange.getIn().getBody();
   
   /*     
        String deploymentId = "com.redhat.emea.example.bfod:bfod-bpm:1.0.0"; // this can be viewed from business-central -> properties of deployed Process
        URL baseUrl = new URL("http://localhost:8080/business-central/");
        String user = "bpmadmin";
        String password = "bpmsuite1!";
        long processId = 22;

        RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(deploymentId, baseUrl, user,
                password);

        RuntimeEngine engine = restSessionFactory.newRuntimeEngine();

        KieSession ksession = engine.getKieSession();

        // The process ID is returned when starting a process
        ksession.signalEvent("complete", true, processId);

        //exchange.getOut().setBody(a, AccountDetails.class);
        */
    }

}

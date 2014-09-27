package com.redhat.emea.bfod.proxy.service;

import java.util.List;

public class PlaceOrderResponse {
	public String transactionId;
	public List<String> validationMessages;	

	public PlaceOrderResponse(final String transactionId, final List<String> validationMessages){
		this.transactionId = transactionId;
		this.validationMessages = validationMessages;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public List<String> getValidationMessages() {
		return validationMessages;
	}	
}
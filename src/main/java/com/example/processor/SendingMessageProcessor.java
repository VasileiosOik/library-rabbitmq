package com.example.processor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.CustomMessage;
import com.example.messagehandlers.MessageCreator;

@Component
public class SendingMessageProcessor implements MessageProcessor {

	@Autowired
	private MessageCreator messageCreator;
	
	private static final Logger LOG=LoggerFactory.getLogger(SendingMessageProcessor.class);
	
	public CustomMessage processRequest(CustomMessage message) {
		
		return addedMessage(message);
	}

	private CustomMessage addedMessage(CustomMessage message) {
		LOG.debug("A message has been added to the queue");
		return messageCreator.addMessage(message);
		
	}
}

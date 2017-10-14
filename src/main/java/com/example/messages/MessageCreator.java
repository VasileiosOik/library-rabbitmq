package com.example.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageCreator {

	private static final Logger LOG = LoggerFactory.getLogger(MessageCreator.class);

	public CustomMessage errorMessageCreator(int age) {
		CustomMessage message = constructMessage();
		LOG.debug("Inside the SendingMessageProcessor.class---->The error message added to the queue is: [{}]",
				message);
		return message;
	}

	public CustomMessage addedMessage(CustomMessage message) {
		CustomMessage customMessage = constructMessage();
		LOG.debug("Inside the SendingMessageProcessor.class---->The message added to the queue is: [{}]",
				customMessage);
		return customMessage;
	}

	private CustomMessage constructMessage() {
		CustomMessage message = new CustomMessage();
		message.setName("Bill");
		message.setNumber(29);
		return message;
	}

}

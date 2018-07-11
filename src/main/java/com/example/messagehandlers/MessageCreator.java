package com.example.messagehandlers;

import com.example.domain.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageCreator {

	private static final Logger LOG = LoggerFactory.getLogger(MessageCreator.class);

	public CustomMessage errorMessage() {
		CustomMessage message = constructErrorMessage();
		LOG.debug("Inside the SendingMessageProcessor.class---->The error message added to the queue is: [{}]", message);
		return message;
	}

	private CustomMessage constructErrorMessage() {
		return null;
	}

	public CustomMessage addMessage(CustomMessage message) {
		LOG.debug("Inside the SendingMessageProcessor.class---->The message added to the queue is: [{}]",
				message);
		return message;
	}
}

package com.example.messagehandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.domain.CustomMessage;
import com.example.domain.Header;
import com.example.domain.Payload;
import com.example.domain.Person;

@Component
public class MessageCreator {

	private static final Logger LOG = LoggerFactory.getLogger(MessageCreator.class);

	public CustomMessage errorMessage(int age) {
		CustomMessage message = constructErrorMessage();
		LOG.debug("Inside the SendingMessageProcessor.class---->The error message added to the queue is: [{}]",
				message);
		return message;
	}

	private CustomMessage constructErrorMessage() {
		
		return null;
	}

	public CustomMessage addedMessage(CustomMessage message) {
		CustomMessage customMessage = constructNewPersonDetailsMessage();
		LOG.debug("Inside the SendingMessageProcessor.class---->The message added to the queue is: [{}]",
				customMessage);
		return customMessage;
	}

	private CustomMessage constructNewPersonDetailsMessage() {
		CustomMessage message = new CustomMessage();
		message.setHeader(getHeader());
		message.setPayload(getPayload());
		return message;
	}

	private Payload getPayload() {
		return new Payload(getPerson());
		
	}

	private Person getPerson() {
		Person person=new Person();
		person.setAge(29);
		person.setName("Bill");
		return person;
	}

	private Header getHeader() {
		Header header=new Header();
		header.setMessageId("2");
		header.setEvent("as.changed");
		return header;
	}

}

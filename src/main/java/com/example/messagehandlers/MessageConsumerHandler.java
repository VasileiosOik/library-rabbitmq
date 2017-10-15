package com.example.messagehandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.domain.CustomMessage;
import com.example.domain.Header;
import com.example.domain.Payload;
import com.example.domain.Person;

@Component
public class MessageConsumerHandler {

	private final Logger log = LoggerFactory.getLogger(MessageConsumerHandler.class);

	@Autowired
	private MessagePublisher messagePublisher;

	@Autowired
	private MessageCreator messageCreator;

	@Autowired
	MessageProcessor messageProcessor;

	@Value("${publishing.error.event}")
	private String errorEvent;

	@RabbitListener(containerFactory = "listenerContainer", queues = "simple.queue.bill")
	public void onMessage(CustomMessage message) {
		log.debug("Inside the MessageConsumer class---->The message which consumed is: {}", message);

		try {
			log.debug("Calling the processor...");
			messageProcessor.processRequest(message);
		} catch (Exception e) {
			log.debug("Encountered an error: ", errorMessageCreator(message.getPayload().getPerson().getAge()));
		}

		CustomMessage message2=new CustomMessage();
		message2.setHeader(getHeader());
		message2.setPayload(getPayload());
		log.debug("The new message that I send [{}]", message2);
		messagePublisher.sendMessage(message2);

	}
	
	private Payload getPayload() {
		return new Payload(getPerson());
		
	}

	private Person getPerson() {
		Person person=new Person();
		person.setAge(30);
		person.setName("Alex");
		return person;
	}

	private Header getHeader() {
		Header header=new Header();
		header.setMessageId("3");
		header.setEvent("as.anotherName");
		return header;
	}


	private CustomMessage errorMessageCreator(int age) {
		return messageCreator.errorMessage(age);
	}

}

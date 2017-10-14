package com.example.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
			log.debug("Encountered an error: ", errorMessageCreator(message.getNumber()));
		}

		CustomMessage message2=new CustomMessage();
		message2.setName("Alex");
		message2.setNumber(30);
		message2.setEvent("as.message.saved");
		log.debug("The new message that I send [{}]", message2);
		messagePublisher.sendMessage(message2);

	}

	private CustomMessage errorMessageCreator(int age) {
		return messageCreator.errorMessageCreator(age);
	}

}

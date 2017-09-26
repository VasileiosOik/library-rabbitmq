package com.example.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

	private final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

	@RabbitListener(containerFactory = "listenerContainer", queues = "simple.queue.bill")
	public void onMessage(CustomMessage message) {
		String parseMessage = message.getName() + " " + message.getNumber();
		log.debug("The message is: {}", parseMessage);
	}

}

package com.example.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerAndPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumerAndPublisher.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	// Scheduled task to send an object every 5 seconds

	// @Scheduled(fixedDelay = 5000)
	public void sendMessage(CustomMessage message) {
		CustomMessage ex = new CustomMessage(message.getNumber(), message.getName());
		LOGGER.info("Sending example message {}, {}", ex.getName(), ex.getNumber());
		rabbitTemplate.convertAndSend(ex);
	}

	// Annotation to listen for an ExampleObject

	@RabbitListener(containerFactory = "listenerContainer", queues = "simple.queue.bill")
	public void handleMessage(CustomMessage customMessage) {
		// LOGGER.info("Received incoming message {}", (String)
		// rabbitTemplate.receiveAndConvert("simple.queue.bill"));
		LOGGER.info("Received message... {}, {}", customMessage.getNumber(), customMessage.getName());
	}

}

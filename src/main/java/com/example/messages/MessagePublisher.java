package com.example.messages;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

	@Autowired
	RabbitTemplate rabbitTemplate;

	// @Scheduled(fixedDelay = 5000)
	public void send(CustomMessage message) {
		message = new CustomMessage(1, "Bill");
		rabbitTemplate.convertAndSend(message);
	}

}

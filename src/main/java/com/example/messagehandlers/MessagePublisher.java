package com.example.messagehandlers;


import com.example.configuration.RabbitMqProducerConfig;
import com.example.domain.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

	private static final Logger LOG = LoggerFactory.getLogger(MessagePublisher.class);

	@Autowired
	private RabbitMqProducerConfig rabbitMqProducerConfig;

	public void sendMessage(final CustomMessage message) {
		LOG.debug("Inside the MesagePublisher----->Publishing message to the queue: [{}]", message);
		RabbitTemplate rabbitTemplate = getRabbitTemplate(message);
		rabbitTemplate.convertAndSend(message, message1 -> message1);
	}

	private RabbitTemplate getRabbitTemplate(final CustomMessage message) {
		return rabbitMqProducerConfig.rabbitTemplate(message.getHeader().getEvent());
	}

}

package com.example.messagehandlers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configuration.RabbitMqPublisherConfig;
import com.example.domain.CustomMessage;

@Component
public class MessagePublisher {

	private static final Logger LOG = LoggerFactory.getLogger(MessagePublisher.class);

	@Autowired
	private RabbitMqPublisherConfig rabbitMqPublisherConfig;

	public void sendMessage(final CustomMessage message) {
		LOG.debug("Inside the MesagePublisher----->Publishing message to the queue: [{}]", message);
		RabbitTemplate rabbitTemplate = getRabbitTemplate(message);
		rabbitTemplate.convertAndSend(message, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException {
				return message;
			}
		});
	}

	private RabbitTemplate getRabbitTemplate(final CustomMessage message) {
		return rabbitMqPublisherConfig.rabbitTemplate(message.getHeader().getEvent());
	}

}

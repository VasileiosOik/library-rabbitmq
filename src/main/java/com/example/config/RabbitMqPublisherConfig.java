package com.example.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMqPublisherConfig {

	private static final String SIMPLE_MESSAGE_QUEUE = "simple.queue.bill";

	@Autowired
	private RabbitMqConnectionConfig rabbitMQConnectionConfig;

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(rabbitMQConnectionConfig.connectionFactory());
		// The routing key is set to the name of the queue by the broker for the default
		// exchange.
		template.setRoutingKey(SIMPLE_MESSAGE_QUEUE);
		template.setQueue(SIMPLE_MESSAGE_QUEUE);

		// Where we will synchronously receive messages from
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}

}

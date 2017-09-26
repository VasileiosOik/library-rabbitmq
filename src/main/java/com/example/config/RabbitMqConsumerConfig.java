package com.example.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMqConsumerConfig {

	private static final String SIMPLE_MESSAGE_QUEUE = "simple.queue.bill";

	@Autowired
	private RabbitMqConnectionConfig rabbitMQConnectionConfig;

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new JsonMessageConverter();
	}

	@Bean
	public Queue simpleQueue() {
		return new Queue(SIMPLE_MESSAGE_QUEUE, true, false, false);
	}

	@Bean
	public SimpleRabbitListenerContainerFactory listenerContainer() {
		SimpleRabbitListenerContainerFactory listenerContainer = new SimpleRabbitListenerContainerFactory();
		listenerContainer.setConnectionFactory(rabbitMQConnectionConfig.connectionFactory());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		listenerContainer.setConcurrentConsumers(3);
		listenerContainer.setMaxConcurrentConsumers(10);
		return listenerContainer;
	}
}

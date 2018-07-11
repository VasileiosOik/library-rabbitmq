package com.example.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:application.properties")
public class RabbitMqProducerConfig {
	
	private static final Logger LOG=LoggerFactory.getLogger(RabbitMqProducerConfig.class);

	@Autowired
	private RabbitMqConnectionConfig rabbitMQConnectionConfig;
	
	@Autowired
	private TopicExchange rabbitExchange;

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	public RabbitTemplate rabbitTemplate(String routingKey) {
		LOG.debug("Creating Rabbit template with routing key [{}]", routingKey);
		RabbitTemplate template = new RabbitTemplate(rabbitMQConnectionConfig.connectionFactory());
		// The routing key is set to the name of the queue by the broker for the default
		// exchange but in my case i use an actual routing key
		template.setRoutingKey(routingKey);
		//name of the exchange
		LOG.debug("Exchange name is: {}", rabbitExchange.getName());
		template.setExchange(rabbitExchange.getName());
		// Where we will synchronously receive messages from
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
}

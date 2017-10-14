package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@EnableRabbit
@Configuration
@PropertySource("classpath:application.properties")
public class RabbitMqPublisherConfig {
	
	private static final Logger LOG=LoggerFactory.getLogger(RabbitMqPublisherConfig.class);

	@Autowired
	private RabbitMqConnectionConfig rabbitMQConnectionConfig;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	public RabbitTemplate rabbitTemplate(String routingKey) {
		LOG.debug("Creating Rabbit template with routing key [{}]", routingKey);
		RabbitTemplate template = new RabbitTemplate(rabbitMQConnectionConfig.connectionFactory());
		// The routing key is set to the name of the queue by the broker for the default
		// exchange.
		template.setRoutingKey(routingKey);

		template.setExchange(exchangeName);

		// Where we will synchronously receive messages from
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
	
	@Bean 
	public TopicExchange messageExchange() {
		LOG.debug("Creating Exchange");
		return new TopicExchange(exchangeName);
	}

}

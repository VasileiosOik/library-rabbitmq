package com.example.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
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
public class RabbitMqConsumerConfig {

	private static final String SIMPLE_MESSAGE_QUEUE = "simple.queue.bill";
	
	private static final Logger LOG=LoggerFactory.getLogger(RabbitMqConsumerConfig.class);

	@Autowired
	private RabbitMqConnectionConfig rabbitMQConnectionConfig;
	
	@Value("${consuming.event}")
	private List<String> bindingKeys;

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Queue consumerQueue() {
		LOG.debug("The application will consume messages from this queue [{}]", SIMPLE_MESSAGE_QUEUE);
		return new Queue(SIMPLE_MESSAGE_QUEUE, true, false, false);
	}
	
	@Bean
	public List<Binding> consumerBinding(TopicExchange messageExchange, Queue consumerQueue){
		List<Binding> bindings=new ArrayList<>();
		LOG.debug("[{}], [{}], [{}]", messageExchange, consumerQueue, bindingKeys);
		bindingKeys.forEach(key->
			bindings.add(BindingBuilder.bind(consumerQueue).to(messageExchange).with(key))
		);
		
		return bindings;
		
	}

	@Bean
	public SimpleRabbitListenerContainerFactory listenerContainer() {
		SimpleRabbitListenerContainerFactory listenerContainer = new SimpleRabbitListenerContainerFactory();
		listenerContainer.setConnectionFactory(rabbitMQConnectionConfig.connectionFactory());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		
		listenerContainer.setPrefetchCount(1);
		listenerContainer.setConcurrentConsumers(5);
		listenerContainer.setMaxConcurrentConsumers(10);
		return listenerContainer;
	}
}

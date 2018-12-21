package com.connection.configuration;

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
@PropertySource("classpath:common.properties")
public class RabbitMqProducerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqProducerConfig.class);

    private final RabbitMqConnectionConfig rabbitMQConnectionConfig;

    private final TopicExchange topicExchange;

    @Autowired
    public RabbitMqProducerConfig(RabbitMqConnectionConfig rabbitMQConnectionConfig, TopicExchange rabbitExchange) {
        this.rabbitMQConnectionConfig = rabbitMQConnectionConfig;
        this.topicExchange = rabbitExchange;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    public RabbitTemplate rabbitTemplate(String routingKey) {
        LOG.debug("Creating Rabbit template with routing key [{}]", routingKey);
        RabbitTemplate template = new RabbitTemplate(rabbitMQConnectionConfig.connectionFactory());
        // The routing key is set to the name of the queue by the broker for the default exchange
        template.setRoutingKey(routingKey);
        //name of the exchange
        LOG.debug("Exchange name is: {}", topicExchange.getName());
        template.setExchange(topicExchange.getName());

        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}

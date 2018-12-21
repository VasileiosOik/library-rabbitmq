package com.connection.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableRabbit
@PropertySource("classpath:common.properties")
public class RabbitMqConnectionConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqConnectionConfig.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        logConfiguration();
        return connectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange rabbitExchange() {
        return new TopicExchange(exchangeName);
    }

    private void logConfiguration() {
        logger.info("========================= RabbitMQ Connection =====================");
        logger.info("====== Host: {}", host);
        logger.info("====== Port: {}", port);
        logger.info("====== Username: {}", username);
        logger.info("====== Password: {}", password);
        logger.info("====== VirtualHost: {}", "NO");
        logger.info("===================================================================");
    }
}

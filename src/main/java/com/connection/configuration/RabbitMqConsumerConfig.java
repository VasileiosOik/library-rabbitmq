package com.connection.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@PropertySource("classpath:common.properties")
public class RabbitMqConsumerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitMqConsumerConfig.class);

    private final RabbitMqConnectionConfig rabbitMQConnectionConfig;

    private final MessageConverter jsonMessageConverter;

    @Value("${ext.received.event}")
    private List<String> bindingKeys;

    @Value("${company.queue.name}")
    private String nameQueue;


    @Autowired
    public RabbitMqConsumerConfig(RabbitMqConnectionConfig rabbitMQConnectionConfig, MessageConverter jsonMessageConverter) {
        this.rabbitMQConnectionConfig = rabbitMQConnectionConfig;
        this.jsonMessageConverter = jsonMessageConverter;
    }

    @Bean
    public Queue consumerQueue() {
        LOG.debug("The application will consume messages from this queue [{}]", nameQueue);
        return new Queue(nameQueue, true, false, false);
    }

    @Bean
    public List<Binding> consumerBinding(TopicExchange topicExchange, Queue consumerQueue) {
        List<Binding> bindings = new ArrayList<>();
        LOG.debug("Here are the Queue details: [{}], [{}], [{}]", topicExchange.getName(), consumerQueue, bindingKeys.get(0));
        bindingKeys.forEach(key ->
                bindings.add(BindingBuilder.bind(consumerQueue).to(topicExchange).with(key))
        );
        return bindings;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory listenerContainer = new SimpleRabbitListenerContainerFactory();
        listenerContainer.setConnectionFactory(rabbitMQConnectionConfig.connectionFactory());
        listenerContainer.setMessageConverter(jsonMessageConverter);

        listenerContainer.setPrefetchCount(1);
        listenerContainer.setConcurrentConsumers(5);
        listenerContainer.setMaxConcurrentConsumers(10);
        return listenerContainer;
    }
}

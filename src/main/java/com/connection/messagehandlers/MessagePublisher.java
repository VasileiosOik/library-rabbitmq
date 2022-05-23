package com.connection.messagehandlers;


import com.connection.configuration.RabbitMqProducerConfig;
import com.connection.domain.CustomMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessagePublisher<T> {

    private static final Logger LOG = LoggerFactory.getLogger(MessagePublisher.class);

    private final RabbitMqProducerConfig rabbitMqProducerConfig;

    @Autowired
    public MessagePublisher(RabbitMqProducerConfig rabbitMqProducerConfig) {
        this.rabbitMqProducerConfig = rabbitMqProducerConfig;
    }

    public void sendMessage(final CustomMessage message) {
        LOG.debug("MessagePublisher----->Publishing message to the queue: [{}]", message);
        RabbitTemplate rabbitTemplate = getRabbitTemplate(message);
        rabbitTemplate.convertAndSend(message, messageDTO -> {
            messageDTO.getMessageProperties().setContentType("application/json");
            return messageDTO;
        });
    }

    private RabbitTemplate getRabbitTemplate(final CustomMessage message) {
        return rabbitMqProducerConfig.rabbitTemplate(message.getHeader().getEvent());
    }

}

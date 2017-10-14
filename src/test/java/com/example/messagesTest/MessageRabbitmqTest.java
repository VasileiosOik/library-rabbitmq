package com.example.messagesTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.config.RabbitMqConnectionConfig;
import com.example.config.RabbitMqConsumerConfig;
import com.example.config.RabbitMqPublisherConfig;
import com.example.messages.CustomMessage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RabbitMqConnectionConfig.class, RabbitMqConsumerConfig.class,
		RabbitMqPublisherConfig.class })
public class MessageRabbitmqTest {

	private final Logger log = LoggerFactory.getLogger(MessageRabbitmqTest.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void test1() throws Exception {

		log.info("Sending new custom message...");
		rabbitTemplate.convertAndSend(new CustomMessage(1, "Bill","as.request.received"));
		log.info("Is listener returned ::: " + rabbitTemplate.isReturnListener());
	}
}

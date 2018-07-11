package com.example.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.CustomMessage;
import com.example.messagehandlers.MessagePublisher;

@RestController
public class MessageController {

	@Autowired
	private MessagePublisher messagePublisher;

	private final Logger log = LoggerFactory.getLogger(MessageController.class);

	@PostMapping(value = "/message/sending", consumes = "application/json")
	public ResponseEntity<CustomMessage> maintainMessage(@RequestBody CustomMessage message) {
		log.debug("The message that came to the controller is: [{}]", message);
		if (message.getPayload().getPerson().getName().equals("Bill")) {
			log.debug("I am going to publish a new message to the queue");
			messagePublisher.sendMessage(message);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			log.debug("Different Name");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}

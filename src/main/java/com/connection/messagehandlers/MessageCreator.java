package com.connection.messagehandlers;

import com.connection.domain.CustomMessage;
import com.connection.domain.Header;
import com.connection.domain.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Primary
public class MessageCreator {

    private static final Logger LOG = LoggerFactory.getLogger(MessageCreator.class);

    public CustomMessage constructMessage(String event, Payload payload) {
        LOG.debug("Creating a message");
        CustomMessage customMessage = new CustomMessage();

        Header header = new Header();
        header.setMessageId(UUID.randomUUID().toString());
        header.setEvent(event);
        customMessage.setHeader(header);

        customMessage.setPayload(payload);
        return customMessage;
    }
}

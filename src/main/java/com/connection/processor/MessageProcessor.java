package com.connection.processor;

import com.connection.domain.CustomMessage;

@FunctionalInterface
public interface MessageProcessor {

	CustomMessage processRequest(CustomMessage message);

}

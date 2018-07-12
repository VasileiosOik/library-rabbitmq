package com.example.processor;

import com.example.domain.CustomMessage;

@FunctionalInterface
public interface MessageProcessor {

	CustomMessage processRequest(CustomMessage message);

}

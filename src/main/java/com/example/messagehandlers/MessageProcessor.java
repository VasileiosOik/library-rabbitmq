package com.example.messagehandlers;

import com.example.domain.CustomMessage;

@FunctionalInterface
public interface MessageProcessor {

	CustomMessage processRequest(CustomMessage message);

}

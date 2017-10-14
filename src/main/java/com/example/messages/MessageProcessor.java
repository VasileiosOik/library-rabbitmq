package com.example.messages;


@FunctionalInterface
public interface MessageProcessor {

	CustomMessage processRequest(CustomMessage message);

}

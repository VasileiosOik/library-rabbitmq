package com.example.messages;

import java.util.List;

public interface MessageProcessor {

	List<CustomMessage> processRequest(CustomMessage message);

}

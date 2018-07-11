package com.example.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Header {

	private String event;

	private String messageId;

	private Map<String, String> keys = new HashMap<>();

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Map<String, String> getKeys() {
		return keys;
	}

	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}
	
	public void addkeys(String key, String value) {
		this.keys.put(key, value);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}

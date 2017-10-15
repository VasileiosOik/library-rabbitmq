package com.example.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class CustomMessage {
	
	private Header header;
	private Payload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

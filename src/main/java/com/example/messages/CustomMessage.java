package com.example.messages;

public class CustomMessage {

	private int number;
	private String name;
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomMessage() {

	}

	public CustomMessage(int number, String name, String event) {
		super();
		this.number = number;
		this.name = name;
		this.event = event;
	}

	@Override
	public String toString() {
		return "CustomMessage [number=" + number + ", name=" + name + ", event=" + event + "]";
	}

}

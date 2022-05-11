package com.project.myapp.payload.response;

import java.util.Date;
public class AllEventsResponse {
	private Long id;
	private String eventName;
	private String eventType;
	private Date eventDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public AllEventsResponse(Long id, String eventName, String eventType,Date eventDate) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate=eventDate;
	}
	public AllEventsResponse() {
		super();
	}
	
}

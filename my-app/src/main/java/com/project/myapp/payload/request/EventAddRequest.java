package com.project.myapp.payload.request;


import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.myapp.models.Batch;

public class EventAddRequest {
	private String eventName;
	private String eventDescription;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date eventDate;
	@Temporal(TemporalType.TIME)
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm",timezone = "Asia/Kolkata")
	 private Date eventTime;

	 private List<String> batches;

	public EventAddRequest(String eventName, String eventDescription, Date eventDate, Date eventTime,
			List<String> batches) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.batches = batches;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public List<String> getBatches() {
		return batches;
	}

	public void setBatches(List<String> batches) {
		this.batches = batches;
	}
	 
	 

	
}

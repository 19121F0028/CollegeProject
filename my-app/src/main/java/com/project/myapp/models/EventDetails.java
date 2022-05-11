package com.project.myapp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class EventDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String eventName;
	private String eventDescription;
	private Date eventDate;
	private Date registrationExpiresAt;
	 private String eventType;
	 private boolean quiz;
	@ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "event_batches",
	            joinColumns = @JoinColumn(
	                    name = "event_details_id", referencedColumnName = "id"
	            ),
	            inverseJoinColumns = @JoinColumn(
	                    name = "batch_id", referencedColumnName = "id"
	            )
	    )
	 private Set<Batch> batches=new HashSet<>();
	@OneToMany
	private List<EventResult> eventResult;
	private Long quizId;

	public EventDetails() {
		super();
	}
	public EventDetails(String eventName, String eventDescription, Date eventDate) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
	}



	public EventDetails(String eventName, String eventDescription, Date eventDate,  String eventType,
			boolean quiz, Set<Batch> batches) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
	
		this.eventType = eventType;
		this.quiz = quiz;
		this.batches = batches;
	}
	public Set<Batch> getBatches() {
		return batches;
	}
	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}
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
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public boolean isQuiz() {
		return quiz;
	}
	public void setQuiz(boolean quiz) {
		this.quiz = quiz;
	}
	public Date getRegistrationExpiresAt() {
		return registrationExpiresAt;
	}
	public void setRegistrationExpiresAt(Date registrationExpiresAt) {
		this.registrationExpiresAt = registrationExpiresAt;
	}
	public EventDetails(String eventName, String eventDescription, Date eventDate,  String eventType,
			boolean quiz) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;

		this.eventType = eventType;
		this.quiz = quiz;
	}
	public EventDetails( String eventName, String eventDescription, Date eventDate, Date registrationExpiresAt,
			 String eventType, boolean quiz, Set<Batch> batches) {
		super();
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.registrationExpiresAt = registrationExpiresAt;

		this.eventType = eventType;
		this.quiz = quiz;
		this.batches = batches;
	}
	
	
	public List<EventResult> getEventResult() {
		return eventResult;
	}
	public void setEventResult(List<EventResult> eventResult) {
		this.eventResult = eventResult;
	}
	public Long getQuizId() {
		return quizId;
	}
	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public void addBatch(Batch b) {
		this.getBatches().add(b);
		b.getEventsRegistered().add(this);
	}
	public void removeBatch(Batch b) {
		this.getBatches().remove(b);
		b.getEventsRegistered().remove(this);
	}

}

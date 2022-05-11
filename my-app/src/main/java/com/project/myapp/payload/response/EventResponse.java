package com.project.myapp.payload.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.myapp.models.Batch;
import com.project.myapp.models.Student;

public class EventResponse {
private Long id;
private  String eventName;
private String eventDescription;
private Date eventDate;
private String eventType;
private boolean quiz;
private List<String> batchesRegistered=new ArrayList<String>();
private int studentsUnRegistered;
private int studentsRegistered;
private int studentsParticipated;
private int facultyUnRegistered;
private int facultyRegistered;
private int facultyParticipated;

private String quizName;
private Long quizId;
private Date registrationsExpiresAt;
public EventResponse() {
	super();
}
public EventResponse(Long id, String eventName, String eventDescription, Date eventDate, String eventType, boolean quiz,
		List<String> batchesRegistered, int studentsUnRegistered, int studentsRegistered, int studentsParticipated,
		int facultyUnRegistered, int facultyRegistered, int facultyParticipated, String quizName, Long quizId) {
	super();
	this.id = id;
	this.eventName = eventName;
	this.eventDescription = eventDescription;
	this.eventDate = eventDate;
	this.eventType = eventType;
	this.quiz = quiz;
	this.batchesRegistered = batchesRegistered;
	this.studentsUnRegistered = studentsUnRegistered;
	this.studentsRegistered = studentsRegistered;
	this.studentsParticipated = studentsParticipated;
	this.facultyUnRegistered = facultyUnRegistered;
	this.facultyRegistered = facultyRegistered;
	this.facultyParticipated = facultyParticipated;
	this.quizName = quizName;
	this.quizId = quizId;
}

public EventResponse(Long id, String eventName, String eventDescription, Date eventDate, String eventType, boolean quiz,
		List<String> batchesRegistered, int studentsUnRegistered, int studentsRegistered, int studentsParticipated,
		int facultyUnRegistered, int facultyRegistered, int facultyParticipated, Date registrationsExpiresAt) {
	super();
	this.id = id;
	this.eventName = eventName;
	this.eventDescription = eventDescription;
	this.eventDate = eventDate;
	this.eventType = eventType;
	this.quiz = quiz;
	this.batchesRegistered = batchesRegistered;
	this.studentsUnRegistered = studentsUnRegistered;
	this.studentsRegistered = studentsRegistered;
	this.studentsParticipated = studentsParticipated;
	this.facultyUnRegistered = facultyUnRegistered;
	this.facultyRegistered = facultyRegistered;
	this.facultyParticipated = facultyParticipated;
	this.registrationsExpiresAt = registrationsExpiresAt;
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
public List<String> getBatchesRegistered() {
	return batchesRegistered;
}
public void setBatchesRegistered(List<String> batchesRegistered) {
	this.batchesRegistered = batchesRegistered;
}
public int getStudentsUnRegistered() {
	return studentsUnRegistered;
}
public void setStudentsUnRegistered(int studentsUnRegistered) {
	this.studentsUnRegistered = studentsUnRegistered;
}
public int getStudentsRegistered() {
	return studentsRegistered;
}
public void setStudentsRegistered(int studentsRegistered) {
	this.studentsRegistered = studentsRegistered;
}
public int getStudentsParticipated() {
	return studentsParticipated;
}
public void setStudentsParticipated(int studentsParticipated) {
	this.studentsParticipated = studentsParticipated;
}
public int getFacultyUnRegistered() {
	return facultyUnRegistered;
}
public void setFacultyUnRegistered(int facultyUnRegistered) {
	this.facultyUnRegistered = facultyUnRegistered;
}
public int getFacultyRegistered() {
	return facultyRegistered;
}
public void setFacultyRegistered(int facultyRegistered) {
	this.facultyRegistered = facultyRegistered;
}
public int getFacultyParticipated() {
	return facultyParticipated;
}
public void setFacultyParticipated(int facultyParticipated) {
	this.facultyParticipated = facultyParticipated;
}
public String getQuizName() {
	return quizName;
}
public void setQuizName(String quizName) {
	this.quizName = quizName;
}
public Long getQuizId() {
	return quizId;
}
public void setQuizId(Long quizId) {
	this.quizId = quizId;
}
public Date getRegistrationsExpiresAt() {
	return registrationsExpiresAt;
}
public void setRegistrationsExpiresAt(Date registrationsExpiresAt) {
	this.registrationsExpiresAt = registrationsExpiresAt;
}

}

package com.project.myapp.payload.response;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AllQuizResponse {

	private Long id;
	private String quizName;
	private Date quizActivationDate;
	private Date quizExpiresDate;
	private Long duration;
	private Long noOfAttempts;
	private int totalMcqs;
	private int studentsattemptedCount;
	public AllQuizResponse() {
		super();
	}
	public AllQuizResponse(Long id, String quizName, Date quizActivationDate, Date quizExpiresDate,
			 Long duration, Long noOfAttempts,int totalMcqs,int studentsattemptedCount) {
		super();
		this.id = id;
		this.quizName = quizName;
		this.quizActivationDate = quizActivationDate;
		this.quizExpiresDate = quizExpiresDate;
		this.duration = duration;
		this.noOfAttempts = noOfAttempts;
		this.totalMcqs=totalMcqs;
		this.studentsattemptedCount=studentsattemptedCount;
	}
	public AllQuizResponse(Long id, String quizName) {
		super();
		this.id = id;
		this.quizName = quizName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public Date getQuizActivationDate() {
		return quizActivationDate;
	}
	public void setQuizActivationDate(Date quizActivationDate) {
		this.quizActivationDate = quizActivationDate;
	}
	public Date getQuizExpiresDate() {
		return quizExpiresDate;
	}
	public void setQuizExpiresDate(Date quizExpiresDate) {
		this.quizExpiresDate = quizExpiresDate;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Long getNoOfAttempts() {
		return noOfAttempts;
	}
	public void setNoOfAttempts(Long noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}
	public int getTotalMcqs() {
		return totalMcqs;
	}
	public void setTotalMcqs(int totalMcqs) {
		this.totalMcqs = totalMcqs;
	}
	public int getStudentsattemptedCount() {
		return studentsattemptedCount;
	}
	public void setStudentsattemptedCount(int studentsattemptedCount) {
		this.studentsattemptedCount = studentsattemptedCount;
	}
	
}

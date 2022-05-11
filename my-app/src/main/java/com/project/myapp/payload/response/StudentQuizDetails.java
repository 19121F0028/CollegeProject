package com.project.myapp.payload.response;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.myapp.models.StudentScore;

public class StudentQuizDetails {
 private String quizName;
	private Date quizActivationDate;
	private Date quizExpiresDate;
	private Long duration;
	private Long noOfAttemptsAllowed;
	private int totalMcqs;
	private int attemtedCount;
	private String quizStatus;
	
	public StudentQuizDetails(String quizName, Date quizActivationDate, Date quizExpiresDate, Long duration,
			Long noOfAttemptsAllowed, int totalMcqs, int attemtedCount, String quizStatus) {
		super();
		this.quizName = quizName;
		this.quizActivationDate = quizActivationDate;
		this.quizExpiresDate = quizExpiresDate;
		this.duration = duration;
		this.noOfAttemptsAllowed = noOfAttemptsAllowed;
		this.totalMcqs = totalMcqs;
		this.attemtedCount = attemtedCount;
		this.quizStatus = quizStatus;
	}
	public StudentQuizDetails() {
		super();
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
	public Long getNoOfAttemptsAllowed() {
		return noOfAttemptsAllowed;
	}
	public void setNoOfAttemptsAllowed(Long noOfAttemptsAllowed) {
		this.noOfAttemptsAllowed = noOfAttemptsAllowed;
	}
	public int getTotalMcqs() {
		return totalMcqs;
	}
	public void setTotalMcqs(int totalMcqs) {
		this.totalMcqs = totalMcqs;
	}
	public int getAttemtedCount() {
		return attemtedCount;
	}
	public void setAttemtedCount(int attemtedCount) {
		this.attemtedCount = attemtedCount;
	}
	public String getQuizStatus() {
		return quizStatus;
	}
	public void setQuizStatus(String quizStatus) {
		this.quizStatus = quizStatus;
	}
	
	
}

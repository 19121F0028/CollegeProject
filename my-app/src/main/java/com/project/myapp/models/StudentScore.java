package com.project.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentScore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rollNo;
	private int score;
	private Long duration;
	private int attempt;
	private int total;
	private int questionsAttempted;
	public StudentScore() {
		super();
	}
	public StudentScore(String rollNo, int score, Long duration, int attempt, int total, int questionsAttempted) {
		super();
		this.rollNo = rollNo;
		this.score = score;
		this.duration = duration;
		this.attempt = attempt;
		this.total = total;
		this.questionsAttempted = questionsAttempted;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getQuestionsAttempted() {
		return questionsAttempted;
	}
	public void setQuestionsAttempted(int questionsAttempted) {
		this.questionsAttempted = questionsAttempted;
	}
	
}

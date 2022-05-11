package com.project.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String rollNo;
	private int score;
	private Long duration;
	private int attempts;
	public StudentAttempt() {
		super();
	}

	public StudentAttempt(String rollNo) {
		super();
		this.rollNo = rollNo;
	}

	public StudentAttempt(String rollNo, int attempts) {
		super();
		this.rollNo = rollNo;
		this.attempts = attempts;
	}

	public StudentAttempt(Long id, String rollNo) {
		super();
		this.id = id;
		this.rollNo = rollNo;
	}

	public StudentAttempt(String rollNo, int score, Long duration, int attempts) {
		super();
		this.rollNo = rollNo;
		this.score = score;
		this.duration = duration;
		this.attempts = attempts;
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

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
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
	
}

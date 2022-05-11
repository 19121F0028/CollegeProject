package com.project.myapp.payload.response;

public class EventResultResponse {
	private String rollNo;
	private int score;
	public EventResultResponse(String rollNo, int score) {
		super();
		this.rollNo = rollNo;
		this.score = score;
	}
	public EventResultResponse() {
		super();
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
	
}

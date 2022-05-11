package com.project.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long facultyId;
	private Long studentId;
	private String rollNo;
	private int marks;
	public EventResult() {
		super();
	}
	public EventResult(Long id, Long facultyId, Long studentId, int marks) {
		super();
		this.id = id;
		this.facultyId = facultyId;
		this.studentId = studentId;
		this.marks = marks;
	}
	public EventResult(Long facultyId, Long studentId, int marks) {
		super();
		this.facultyId = facultyId;
		this.studentId = studentId;
		this.marks = marks;
	}
	public Long getId() {
		return id;
	}public EventResult(Long facultyId, Long studentId, int marks, String rollNo) {
	super();
	this.facultyId = facultyId;
	this.studentId = studentId;
	this.marks = marks;
	this.rollNo = rollNo;
}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
}

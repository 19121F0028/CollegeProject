package com.project.myapp.payload.response;

public class AllRegisterStudentsResponse {
	private Long id;
	private String rollNo;
	private String firstName;
	private String lastName;
	private String batchName;
	
	public AllRegisterStudentsResponse(Long id,String rollNo, String firstName, String lastName, String batchName) {
		super();
		this.id=id;
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.batchName = batchName;
	}
	public AllRegisterStudentsResponse(String rollNo, String firstName, String lastName, String batchName) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.batchName = batchName;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}

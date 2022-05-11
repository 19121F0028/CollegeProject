package com.project.myapp.payload.response;

public class AllStudentResponse {
	private Long id;
	private String rollNo;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String phoneNumber;
	private String batch;
	public AllStudentResponse(Long id,String rollNo, String firstName, String lastName, String email, String gender,
			String phoneNumber, String batch) {
		super();
		this.id=id;
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.batch = batch;
	}
	public AllStudentResponse(String rollNo, String firstName, String lastName, String email, String gender,
			String phoneNumber, String batch) {
		super();
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.batch = batch;
	}
	public AllStudentResponse(Long id, String rollNo, String firstName, String lastName, String batch) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.firstName = firstName;
		this.lastName = lastName;
		this.batch = batch;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

}

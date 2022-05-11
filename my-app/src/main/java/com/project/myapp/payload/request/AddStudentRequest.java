package com.project.myapp.payload.request;

import javax.validation.constraints.NotBlank;

public class AddStudentRequest {

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	@NotBlank
	private String firstName;
	@NotBlank
	private String LastName;
	@NotBlank
	private String email;
	
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String gender;
	
	private String batch;
	
	
	public AddStudentRequest(@NotBlank String username, @NotBlank String password, @NotBlank String firstName,
			@NotBlank String lastName, @NotBlank String email, @NotBlank String phoneNumber, @NotBlank String gender,String batch) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		LastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.batch=batch;
	}
	public AddStudentRequest() {
		super();
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	
	
}

package com.project.myapp.payload.request;

import javax.validation.constraints.NotBlank;


public class StudentUpdateRequest {
	
	@NotBlank
	private String firstName;
	@NotBlank
	private String LastName;
	
	@NotBlank
	private String phoneNumber;
	@NotBlank
	private String gender;
	
	@NotBlank
	private String batch;
	

	public StudentUpdateRequest(@NotBlank String firstName, @NotBlank String lastName,
			 @NotBlank String phoneNumber, @NotBlank String gender, @NotBlank String batch) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.batch = batch;
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

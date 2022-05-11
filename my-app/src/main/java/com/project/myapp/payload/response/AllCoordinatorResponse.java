package com.project.myapp.payload.response;

public class AllCoordinatorResponse {
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	private String phoneNumber;
	
	public AllCoordinatorResponse() {
		super();
	}
	
	

	public AllCoordinatorResponse(Long id, String username, String firstName, String lastName, String email,
			String gender, String phoneNumber) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}



	public AllCoordinatorResponse(Long id,String username, String firstName, String lastName) {
		super();
		this.id=id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}

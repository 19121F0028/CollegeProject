package com.project.myapp.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table( 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "email"),
})
public class HOD extends User {

	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;

	@NotBlank
	private String email;
	@NotBlank
	private String gender;
	
	@NotBlank 
	private String phoneNumber;


	public HOD() {
		super();
	}

	public HOD(String username,String password,@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String gender, @NotBlank String phoneNumber) {
		super(username,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
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

	@Override
	public String toString() {
		return "EventCordinator [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", getId()=" + getId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getRoles()=" + getRoles() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}

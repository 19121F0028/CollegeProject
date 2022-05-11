package com.project.myapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table( 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "email"),
})
public class EventCordinator extends User {

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

	@OneToMany
	private List<Quiz> quizzesAdded;

	public EventCordinator() {
		super();
	}

	public EventCordinator(String username,String password,@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String gender, @NotBlank String phoneNumber) {
		super(username,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
	}

	public EventCordinator(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String gender, @NotBlank String phoneNumber, List<Quiz> quizzesAdded) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.quizzesAdded = quizzesAdded;
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

	public List<Quiz> getQuizzesAdded() {
		return quizzesAdded;
	}

	public void setQuizzesAdded(List<Quiz> quizzesAdded) {
		this.quizzesAdded = quizzesAdded;
	}

	@Override
	public String toString() {
		return "EventCordinator [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", getId()=" + getId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getRoles()=" + getRoles() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}

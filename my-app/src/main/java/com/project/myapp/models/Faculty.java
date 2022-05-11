package com.project.myapp.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table( 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "email"),
})
public class Faculty extends User {

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
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "faculty_events_registered",
            joinColumns = @JoinColumn(
                    name = "faculty_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"
            )
    )
    private Set<Event> eventsRegistered = new HashSet<>();
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "faculty_events_participated",
            joinColumns = @JoinColumn(
                    name = "faculty_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"
            )
    )
    private Set<Event> eventsParticipated = new HashSet<>();

	@OneToMany
	private List<Quiz> quizzesAdded;
	public Faculty() {
		super();
	}

	public Faculty(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String gender, @NotBlank String phoneNumber) {
		super();
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

	public Set<Event> getEventsRegistered() {
		return eventsRegistered;
	}

	public void setEventsRegistered(Set<Event> eventsRegistered) {
		this.eventsRegistered = eventsRegistered;
	}

	public Set<Event> getEventsParticipated() {
		return eventsParticipated;
	}

	public void setEventsParticipated(Set<Event> eventsParticipated) {
		this.eventsParticipated = eventsParticipated;
	}

	public List<Quiz> getQuizzesAdded() {
		return quizzesAdded;
	}

	@Override
	public String toString() {
		return "EventCordinator [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gender="
				+ gender + ", phoneNumber=" + phoneNumber + ", getId()=" + getId() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getRoles()=" + getRoles() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	public void addEventRegistered(Event e) {
		this.eventsRegistered.add(e);
		e.getFacultyRegistered().add(this);
	}
	public void removeEventRegistered(Event e) {
		this.eventsRegistered.remove(e);
		e.getFacultyRegistered().add(this);
	}
	public void setQuizzesAdded(List<Quiz> quizzesAdded) {
		this.quizzesAdded = quizzesAdded;
	}
	public void addEventParticipated(Event e) {
		this.eventsParticipated.add(e);
		e.getFacultyParticipated().add(this);
	}
	public void removeEventParticipated(Event e) {
		this.eventsParticipated.add(e);
		e.getFacultyParticipated().remove(this);
	}
	
	
}

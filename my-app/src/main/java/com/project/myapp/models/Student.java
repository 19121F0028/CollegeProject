package com.project.myapp.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "student", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "email"),
})
public class Student extends User {

	
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
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    private Batch batch;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_events_registered",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"
            )
    )
    private Set<Event> eventsRegistered = new HashSet<>();
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_events_participated",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"
            )
    )
    private Set<Event> eventsParticipated = new HashSet<>();
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_quizzes_attempted",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "quiz_id", referencedColumnName = "id"
            )
    )
    private Set<Event> quizzesAttempted = new HashSet<>();
	public Student() {
		super();
	}

	public Student(@NotBlank String username,@NotBlank String password,@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String gender, @NotBlank String phoneNumber, Batch batch) {
		super(username,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.batch = batch;
	}

	public Student(@NotBlank String firstName, @NotBlank String lastName, @NotBlank String email,
			@NotBlank String gender, @NotBlank String phoneNumber, Batch batch, Set<Event> eventsRegistered,
			Set<Event> eventsParticipated) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.batch = batch;
		this.eventsRegistered = eventsRegistered;
		this.eventsParticipated = eventsParticipated;
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

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
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

	public void addEventRegistered(Event e) {
		this.eventsRegistered.add(e);
		e.getStudentsRegistered().add(this);
	}
	public void removeEventRegistered(Event e) {
		this.eventsRegistered.remove(e);
		e.getStudentsRegistered().remove(this);
	}
	public void addEventParticipated(Event e) {
		this.eventsParticipated.add(e);
		e.getStudentsParticipated().add(this);
	}
	public void removeEventParticipated(Event e) {
		this.eventsParticipated.add(e);
		e.getStudentsParticipated().remove(this);
	}
	

	@Override
	public String toString() {
		return "Student [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", batch=" + batch + ", eventsRegistered=" + eventsRegistered
				+ ", eventsParticipated=" + eventsParticipated + "]";
	}
	
}

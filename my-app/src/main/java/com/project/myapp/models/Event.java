package com.project.myapp.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private EventDetails eventDetails;
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "eventsRegistered")
	private Set<Student> studentsRegistered = new HashSet<>();
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "eventsParticipated")
	private Set<Student> studentsParticipated=new HashSet<>();
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "eventsRegistered")
	private Set<Faculty> facultyRegistered = new HashSet<>();
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "eventsParticipated")
	private Set<Faculty> facultyParticipated=new HashSet<>();

	
	public Event() {
		super();
	}


	public Event(EventDetails eventDetails, Set<Student> studentsRegistered, Set<Student> studentsParticipated,
			Set<Faculty> facultyRegistered, Set<Faculty> facultyParticipated) {
		super();
		this.eventDetails = eventDetails;
		this.studentsRegistered = studentsRegistered;
		this.studentsParticipated = studentsParticipated;
		this.facultyRegistered = facultyRegistered;
		this.facultyParticipated = facultyParticipated;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public EventDetails getEventDetails() {
		return eventDetails;
	}


	public void setEventDetails(EventDetails eventDetails) {
		this.eventDetails = eventDetails;
	}


	public Set<Student> getStudentsRegistered() {
		return studentsRegistered;
	}


	public void setStudentsRegistered(Set<Student> studentsRegistered) {
		this.studentsRegistered = studentsRegistered;
	}


	public Set<Student> getStudentsParticipated() {
		return studentsParticipated;
	}


	public void setStudentsParticipated(Set<Student> studentsParticipated) {
		this.studentsParticipated = studentsParticipated;
	}


	public Set<Faculty> getFacultyRegistered() {
		return facultyRegistered;
	}


	public void setFacultyRegistered(Set<Faculty> facultyRegistered) {
		this.facultyRegistered = facultyRegistered;
	}


	public Set<Faculty> getFacultyParticipated() {
		return facultyParticipated;
	}


	public void setFacultyParticipated(Set<Faculty> facultyParticipated) {
		this.facultyParticipated = facultyParticipated;
	}
	public void removeEventParticipated(Student s) {
		this.getStudentsParticipated().remove(s);
		s.getEventsParticipated().remove(this);
	}
	public void removeEventParticipated(Faculty s) {
		this.getFacultyParticipated().remove(s);
		s.getEventsParticipated().remove(this);
	}
	
	
	
}

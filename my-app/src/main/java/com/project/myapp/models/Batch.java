package com.project.myapp.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String batchName;
	@JsonIgnore
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "batch")
	
	 private Set<Student> students = new HashSet<>();
	@JsonIgnore
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "batches")
	private Set<EventDetails> eventsRegistered = new HashSet<>();
	@JsonIgnore
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "batches")
	private Set<Quiz> quizzesRegistered = new HashSet<>();
	public Batch() {
		super();
	}
	public Batch(Long id, String batchName) {
		super();
		this.id = id;
		this.batchName = batchName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Batch(String batchName) {
		super();
		this.batchName = batchName;
	}
	@Override
	public String toString() {
		return "Batch [id=" + id + ", batchName=" + batchName + ", students=" + students + "]";
	}
	public Batch(Long id, String batchName, Set<Student> students) {
		super();
		this.id = id;
		this.batchName = batchName;
		this.students = students;
	}
	public Batch(String batchName, Set<Student> students) {
		super();
		this.batchName = batchName;
		this.students = students;
	}
	public Set<EventDetails> getEventsRegistered() {
		return eventsRegistered;
	}
	public void setEventsRegistered(Set<EventDetails> eventsRegistered) {
		this.eventsRegistered = eventsRegistered;
	}
	public Set<Quiz> getQuizzesRegistered() {
		return quizzesRegistered;
	}
	public void setQuizzesRegistered(Set<Quiz> quizzesRegistered) {
		this.quizzesRegistered = quizzesRegistered;
	}
	
}

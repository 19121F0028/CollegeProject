package com.project.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String choice;
	public Choice() {
		super();
	}
	public Choice(Long id, String choice) {
		super();
		this.id = id;
		this.choice = choice;
	}
	public Choice(String choice) {
		super();
		this.choice = choice;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
	
}

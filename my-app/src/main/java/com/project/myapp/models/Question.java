package com.project.myapp.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String question;
	@OneToMany
	private List<Choice> choices;
	private String answer;
	@Column(columnDefinition = "TEXT")
	private String codeSnippet;
	public Question() {
		super();
	}
	public Question(Long id, String question, List<Choice> choices, String answer) {
		super();
		this.id = id;
		this.question = question;
		this.choices = choices;
		this.answer = answer;
	}
	public Question(String question, List<Choice> choices, String answer) {
		super();
		this.question = question;
		this.choices = choices;
		this.answer = answer;
	}
	public Question( String question, List<Choice> choices, String answer, String codeSnippet) {
		super();
		this.question = question;
		this.choices = choices;
		this.answer = answer;
		this.codeSnippet = codeSnippet;
	}
	public String getCodeSnippet() {
		return codeSnippet;
	}
	public void setCodeSnippet(String codeSnippet) {
		this.codeSnippet = codeSnippet;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}

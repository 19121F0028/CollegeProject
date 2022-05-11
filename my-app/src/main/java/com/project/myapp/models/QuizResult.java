package com.project.myapp.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class QuizResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private List<StudentScore> students=new ArrayList<StudentScore>();
	public QuizResult() {
		super();
	}
	public QuizResult(List<StudentScore> students) {
		super();
		this.students = students;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<StudentScore> getStudents() {
		return students;
	}
	public void setStudents(List<StudentScore> students) {
		this.students = students;
	}
}
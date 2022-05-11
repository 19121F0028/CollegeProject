package com.project.myapp.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(	name = "quiz", 
uniqueConstraints = { 
	@UniqueConstraint(columnNames = "quizName"),
})
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String quizName;
	private Date quizActivationDate;
	private Date quizExpiresDate;
	private Long duration;
	private Long noOfAttempts;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "quiz_batches",
            joinColumns = @JoinColumn(
                    name = "quiz_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "batch_id", referencedColumnName = "id"
            )
    )
 private Set<Batch> batches=new HashSet<>();
	@OneToMany
	private List<Question> questions;
	@ManyToMany
	private List<StudentAttempt> studentsAttempted = new ArrayList<StudentAttempt>();
	@OneToOne
	private QuizResult quizResult;
	public Quiz() {
		super();
	}
	public Quiz(Long id, String quizName, List<Question> questions) {
		super();
		this.id = id;
		this.quizName = quizName;
		this.questions = questions;
	}
	public Quiz(String quizName, List<Question> questions) {
		super();
		this.quizName = quizName;
		this.questions = questions;
	}
	public Quiz(String quizName, Date quizActivationDate, Long duration, Long noOfAttempts,
			Set<Batch> batches, List<Question> questions, List<StudentAttempt> studentsAttempted) {
		super();
		this.quizName = quizName;
		this.quizActivationDate = quizActivationDate;
		this.duration = duration;
		this.noOfAttempts = noOfAttempts;
		this.batches = batches;
		this.questions = questions;
		this.studentsAttempted = studentsAttempted;
	}
	public Quiz(String quizName, Date quizActivationDate, Date quizExpiresDate,Long duration,
			Long noOfAttempts, Set<Batch> batches, List<Question> questions, List<StudentAttempt> studentsAttempted,
			QuizResult quizResult) {
		super();
		this.quizName = quizName;
		this.quizActivationDate = quizActivationDate;
		this.quizExpiresDate = quizExpiresDate;
		this.duration = duration;
		this.noOfAttempts = noOfAttempts;
		this.batches = batches;
		this.questions = questions;
		this.studentsAttempted = studentsAttempted;
		this.quizResult = quizResult;
	}
	public Date getQuizExpiresDate() {
		return quizExpiresDate;
	}
	public void setQuizExpiresDate(Date quizExpiresDate) {
		this.quizExpiresDate = quizExpiresDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public Date getQuizActivationDate() {
		return quizActivationDate;
	}
	public void setQuizActivationDate(Date quizActivationDate) {
		this.quizActivationDate = quizActivationDate;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Long getNoOfAttempts() {
		return noOfAttempts;
	}
	public void setNoOfAttempts(Long noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}
	public Set<Batch> getBatches() {
		return batches;
	}
	public void setBatches(Set<Batch> batches) {
		this.batches = batches;
	}
	public List<StudentAttempt> getStudentsAttempted() {
		return studentsAttempted;
	}
	public void setStudentsAttempted(List<StudentAttempt> studentsAttempted) {
		this.studentsAttempted = studentsAttempted;
	}
	public QuizResult getQuizResult() {
		return quizResult;
	}
	public void setQuizResult(QuizResult quizResult) {
		this.quizResult = quizResult;
	}
	public void addBatch(Batch b) {
		this.getBatches().add(b);
		b.getQuizzesRegistered().add(this);
	}
	public void removeBatch(Batch b) {
		this.getBatches().remove(b);
		b.getQuizzesRegistered().remove(this);
	}

}

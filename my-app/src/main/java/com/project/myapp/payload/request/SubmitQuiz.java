package com.project.myapp.payload.request;

public class SubmitQuiz {
private Long questionId;
private String answer;
public SubmitQuiz(Long questionId, String answer) {
	super();
	this.questionId = questionId;
	this.answer = answer;
}
public Long getQuestionId() {
	return questionId;
}
public void setQuestionId(Long questionId) {
	this.questionId = questionId;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}

}

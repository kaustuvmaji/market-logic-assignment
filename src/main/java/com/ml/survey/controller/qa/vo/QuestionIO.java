package com.ml.survey.controller.qa.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class QuestionIO implements Serializable {

	private static final long serialVersionUID = 8540428370304393286L;

	private Long questionId;
	@NotBlank
	private String content;
	private List<AnswerIO> answers;

	public QuestionIO() {
	}

	public QuestionIO(Long questionId, String content) {
		super();
		this.questionId = questionId;
		this.content = content;
	}

	public void addAnswer(AnswerIO answer) {
		if (this.answers == null || this.answers.isEmpty()) {
			this.answers = new ArrayList<AnswerIO>();
		}
		this.answers.add(answer);
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(answers, content, questionId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof QuestionIO)) {
			return false;
		}
		QuestionIO other = (QuestionIO) obj;
		return Objects.equals(answers, other.answers) && Objects.equals(content, other.content) && Objects.equals(questionId, other.questionId);
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", content=" + content + ", answers=" + answers + "]";
	}

	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the answers
	 */
	public List<AnswerIO> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<AnswerIO> answers) {
		this.answers = answers;
	}

}
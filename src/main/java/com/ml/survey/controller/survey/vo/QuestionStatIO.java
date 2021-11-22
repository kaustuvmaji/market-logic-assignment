package com.ml.survey.controller.survey.vo;

import java.io.Serializable;
import java.util.List;

public class QuestionStatIO implements Serializable, Comparable<QuestionStatIO> {

	private static final long serialVersionUID = -814878367294558702L;

	Long questionId;
	String content;
	Long counter;
	List<AnswerStatIO> answerStatistics;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public List<AnswerStatIO> getAnswerStatistics() {
		return answerStatistics;
	}

	public void setAnswerStatistics(List<AnswerStatIO> answerStatistics) {
		this.answerStatistics = answerStatistics;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(QuestionStatIO o) {
		return o.counter.compareTo(this.counter);
	}
}
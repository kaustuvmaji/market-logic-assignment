package com.ml.survey.controller.survey.vo;

import java.io.Serializable;

public class AnswerStatIO implements Serializable, Comparable<AnswerStatIO> {

	private static final long serialVersionUID = -814878367294558702L;

	Long answerId;
	String content;
	Long counter;

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getCounter() {
		return counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int compareTo(AnswerStatIO o) {
		
		return o.counter.compareTo(this.counter);
	}
	
	
}


package com.ml.survey.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SurveyResponseQA implements Serializable{
	
	private static final long serialVersionUID = 6915376693231587090L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long surveyId;
	private Long questionId;
	private Long answerId;
	
	@ManyToOne
	SurveyUserFeed surveyUserFeed;
	
	public SurveyResponseQA() {
		
	}

	public SurveyResponseQA(Long questionId, Long answerId, Long surveyId) {
		super();
		this.questionId = questionId;
		this.answerId = answerId;
		this.surveyId = surveyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public SurveyUserFeed getSurveyResponse() {
		return surveyUserFeed;
	}

	public void setSurveyResponse(SurveyUserFeed surveyResponse) {
		this.surveyUserFeed = surveyResponse;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	
}

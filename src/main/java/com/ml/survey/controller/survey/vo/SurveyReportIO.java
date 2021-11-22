package com.ml.survey.controller.survey.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SurveyReportIO implements Serializable {

	private static final long serialVersionUID = 7645073891724374111L;
	private Long surveyId;
	private String surveyDescriptions;
	private String topic;
	private String createdBy;
	private Date createdOn;
	private Long participationCounter;
	private List<QuestionStatIO> questionStatistics;

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyDescriptions() {
		return surveyDescriptions;
	}

	public void setSurveyDescriptions(String surveyDescriptions) {
		this.surveyDescriptions = surveyDescriptions;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getParticipationCounter() {
		return participationCounter;
	}

	public void setParticipationCounter(Long participationCounter) {
		this.participationCounter = participationCounter;
	}

	public List<QuestionStatIO> getQuestionStatistics() {
		return questionStatistics;
	}

	public void setQuestionStatistics(List<QuestionStatIO> questionStatistics) {
		this.questionStatistics = questionStatistics;
	}
}


package com.ml.survey.controller.survey.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.ml.survey.controller.qa.vo.QuestionIO;

public class SurveyIO implements Serializable {

	private static final long serialVersionUID = -2679417805579988656L;
	private Long surveyId;
	private String surveyDescriptions;
	private String topic;
	private Set<QuestionIO> questions;
	private String createdBy;
	private Date createdOn;

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

	public Set<QuestionIO> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionIO> questions) {
		this.questions = questions;
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

	@Override
	public int hashCode() {
		return Objects.hash(surveyId, topic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveyIO)) {
			return false;
		}
		SurveyIO other = (SurveyIO) obj;
		return Objects.equals(surveyId, other.surveyId) && Objects.equals(topic, other.topic);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SurveyIO [");
		if (surveyId != null) {
			builder.append("surveyId=");
			builder.append(surveyId);
			builder.append(", ");
		}
		if (surveyDescriptions != null) {
			builder.append("surveyDescriptions=");
			builder.append(surveyDescriptions);
			builder.append(", ");
		}
		if (topic != null) {
			builder.append("topic=");
			builder.append(topic);
			builder.append(", ");
		}
		if (questions != null) {
			builder.append("questions=");
			builder.append(questions);
			builder.append(", ");
		}
		if (createdBy != null) {
			builder.append("createdBy=");
			builder.append(createdBy);
			builder.append(", ");
		}
		if (createdOn != null) {
			builder.append("createdOn=");
			builder.append(createdOn);
		}
		builder.append("]");
		return builder.toString();
	}


}

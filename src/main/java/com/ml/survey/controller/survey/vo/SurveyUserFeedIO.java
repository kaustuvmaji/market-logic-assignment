package com.ml.survey.controller.survey.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;


public class SurveyUserFeedIO implements Serializable {

	private static final long serialVersionUID = 6477721749730489423L;

	private Long id;
	private Long surveyId;
	private String userInfo;
	private HashMap<Long, Long> questionAndAnswerRef; 
	private String feedback;
	private Long duration;
	private Boolean isDraft;
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getSurveyId() {
		return surveyId;
	}
	
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	
	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public HashMap<Long, Long> getQuestionAndAnswerRef() {
		return questionAndAnswerRef;
	}

	public void setQuestionAndAnswerRef(HashMap<Long, Long> questionAndAnswerRef) {
		this.questionAndAnswerRef = questionAndAnswerRef;
	}

	public String getFeedback() {
		return feedback;
	}
	
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	
	public Long getDuration() {
		return duration;
	}
	
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	

	public Boolean getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(Boolean isDraft) {
		this.isDraft = isDraft;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, surveyId, userInfo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveyUserFeedIO)) {
			return false;
		}
		SurveyUserFeedIO other = (SurveyUserFeedIO) obj;
		return Objects.equals(id, other.id) && Objects.equals(surveyId, other.surveyId) && Objects.equals(userInfo, other.userInfo);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SurveyUserFeedIO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (surveyId != null) {
			builder.append("surveyId=");
			builder.append(surveyId);
			builder.append(", ");
		}
		if (userInfo != null) {
			builder.append("userId=");
			builder.append(userInfo);
			builder.append(", ");
		}
		if (questionAndAnswerRef != null) {
			builder.append("questionAndAnswerRef=");
			builder.append(questionAndAnswerRef);
			builder.append(", ");
		}
		if (feedback != null) {
			builder.append("feedback=");
			builder.append(feedback);
			builder.append(", ");
		}
		if (duration != null) {
			builder.append("duration=");
			builder.append(duration);
		}
		builder.append("]");
		return builder.toString();
	}
}

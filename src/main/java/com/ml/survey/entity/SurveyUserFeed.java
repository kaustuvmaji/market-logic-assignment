package com.ml.survey.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SurveyUserFeed implements Serializable{

	private static final long serialVersionUID = 5884295524753220494L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long surveyId;

	private String userInfo;

	public SurveyUserFeed (){
		
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<SurveyResponseQA> surveyAnswerList;

	public List<SurveyResponseQA> getSurveyAnswerList() {
		return surveyAnswerList;
	}

	public void setSurveyAnswerList(List<SurveyResponseQA> surveyAnswerList) {
		this.surveyAnswerList = surveyAnswerList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
		
}
package com.ml.survey.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SurveyQuestion implements Serializable{
	
	private static final long serialVersionUID = -5794799584075546121L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long questionId; 
	
	public SurveyQuestion() {
		
	}
	
	public SurveyQuestion(Long questionid) {
		super();
		this.questionId = questionid;
	}
	public Long getId() { 
		return id; 
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuestionid() {
		return questionId;
	}
	public void setQuestionid(Long questionid) {
		this.questionId = questionid;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, questionId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveyQuestion)) {
			return false;
		}
		SurveyQuestion other = (SurveyQuestion) obj;
		return Objects.equals(id, other.id) && Objects.equals(questionId, other.questionId);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SurveyQuestion [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (questionId != null) {
			builder.append("questionid=");
			builder.append(questionId);
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}

package com.ml.survey.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="answer")
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {

	private static final long serialVersionUID = -8395685113300109640L;

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="answer_id")
	@Id
	private Long answerId;

	@Lob
	private String content;

	@ManyToOne()
	private Question question;

	public Answer() {
	}
	
	public Answer(String content) {
		this.content = content;
	}

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public int hashCode() {
		return Objects.hash(answerId, content, question);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		return Objects.equals(answerId, other.answerId) && Objects.equals(content, other.content) && Objects.equals(question, other.question);
	}

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", content=" + content + ", viewCount=" +   ", question=" + question + "]";
	}
}
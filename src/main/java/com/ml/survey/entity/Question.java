package com.ml.survey.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the Question database table.
 * @author Kaustuv Maji
 */
@Entity
@Table(name="question")
@NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
public class Question implements Serializable {

	private static final long serialVersionUID = 8540428370304393286L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "question_id")
	private Long questionId;

	@Lob
	private String content;

	//bi-directional many-to-one association to Answer
	@OneToMany(cascade=CascadeType.ALL)
	private Set<Answer> answers;
	
	public Question() {
	}

	public Question(String content) {
		this.content = content;
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


	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setQuestion(null);
		return answer;
	}
	
	public void addAnswer(Answer answer) {
		if (this.answers == null) {
			this.answers = new HashSet<Answer>();
		}
		this.answers.add(answer);
	}

	public boolean isAnswerPresent(String answerContent) {
		Set<String> answerContents = new HashSet<String>();
		this.getAnswers().forEach(e -> {
			answerContents.add(e.getContent());
		});
		return answerContents.contains(answerContent);
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
		if (!(obj instanceof Question)) {
			return false;
		}
		Question other = (Question) obj;
		return Objects.equals(answers, other.answers) && Objects.equals(content, other.content) && Objects.equals(questionId, other.questionId);
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", content=" + content + ", viewCount="  + ", answers=" + answers + "]";
	}
}
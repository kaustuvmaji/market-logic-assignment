package com.ml.survey.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "survey")
public class Survey implements Serializable {

	private static final long serialVersionUID = -7318447912435142769L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "surveyDecription")
	private String surveyDescription;

	@Column(name = "topic")
	private String topic;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "createdOn")
	@Temporal(TemporalType.DATE)
	private Date createdOn;

	@Column(name = "participationCounter")
	private Long participationCounter;

	@OneToMany(cascade = CascadeType.ALL)
	List<SurveyQuestion> surveyQuestions;

	public Survey() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSurveyDescription() {
		return surveyDescription;
	}

	public void setSurveyDescription(String surveyDescription) {
		this.surveyDescription = surveyDescription;
	}

	public List<SurveyQuestion> getSurveyQuestions() {
		return surveyQuestions;
	}

	public void setSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}

//	public List<SurveyUserFeed> getSurveyUserFeeds() {
//		return surveyUserFeeds;
//	}
//	
//
//	public void setSurveyUserFeeds(List<SurveyUserFeed> surveyUserFeeds) {
//		this.surveyUserFeeds = surveyUserFeeds;
//	}

//	public void addSurveyUserFeed (SurveyUserFeed suf) {
//		if(null == this.surveyUserFeeds && this.surveyUserFeeds.isEmpty()) {
//			this.surveyUserFeeds = new ArrayList<SurveyUserFeed>();
//		}
//		this.surveyUserFeeds.add(suf);
//	}

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

	@Override
	public int hashCode() {
		return Objects.hash(id, surveyDescription, surveyQuestions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Survey)) {
			return false;
		}
		Survey other = (Survey) obj;
		return Objects.equals(id, other.id) && Objects.equals(surveyDescription, other.surveyDescription) && Objects.equals(surveyQuestions, other.surveyQuestions);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Survey [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (surveyDescription != null) {
			builder.append("surveyDescription=");
			builder.append(surveyDescription);
			builder.append(", ");
		}
		if (topic != null) {
			builder.append("topic=");
			builder.append(topic);
			builder.append(", ");
		}
		if (surveyQuestions != null) {
			builder.append("surveyQuestions=");
			builder.append(surveyQuestions);
			builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}
}

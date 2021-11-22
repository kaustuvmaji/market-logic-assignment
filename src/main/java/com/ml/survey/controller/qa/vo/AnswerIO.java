package com.ml.survey.controller.qa.vo;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnswerIO implements Serializable {

	private static final long serialVersionUID = -7526782339574003381L;
	@NotNull
	private Long id;
	@NotBlank
	private String content;
	
	public AnswerIO() {
		
	}
	public AnswerIO(Long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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


	@Override
	public int hashCode() {
		return Objects.hash(content, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AnswerIO)) {
			return false;
		}
		AnswerIO other = (AnswerIO) obj;
		return Objects.equals(content, other.content) && Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + "]";
	}



}
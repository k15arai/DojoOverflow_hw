package com.kenjiarai.dojooverflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="questions")
public class Question {

	@Id // auto-generated
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Question is mandatory")
	private String question;
	
	// This will not allow the createdAt column to be updated after creation
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist // save before the object is created
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate // save the date for objects being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<TagQuestion> getTags() {
		return tags;
	}

	public void setTags(List<TagQuestion> tags) {
		this.tags = tags;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@OneToMany(mappedBy="question", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}) // mappedBy must match attribute in child
	private List<Answer> answers;
	
	// part of Many-to-Many relationship
	@OneToMany(mappedBy="question", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JsonIgnore // not really necessary
	private List<TagQuestion> tags;
	
	public Question() {}
		
}

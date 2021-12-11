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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tags")
public class Tag {
	
	@Id // auto-generated
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String subject;
	
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<TagQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<TagQuestion> questions) {
		this.questions = questions;
	}

	// part of Many to Many relationship
	@OneToMany(mappedBy="tag", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JsonIgnore // not necessary
	private List<TagQuestion> questions;
	
	public Tag() {}
	
	public Tag(String subject) {
		this.subject = subject;
	}

	
}

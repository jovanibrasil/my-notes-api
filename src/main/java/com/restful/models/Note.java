package com.restful.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {

	@Id
	private String id;
	private String title;
	private String text;
	private Date lastModifiedOn;
	private String notebookId;
	private String userId;

	public Note() {}
	
	public Note(String id, String title, String text, String notebookId, String userId) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.lastModifiedOn = new Date();
		this.notebookId = notebookId;
		this.userId = userId;
	}
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getText() {
		return text;
	}
	public Date getLastModifiedOn() {
		return lastModifiedOn;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setLastModifiedOn(Date lastModifiedOn) {
		this.lastModifiedOn = lastModifiedOn;
	}

	public String getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(String notebookId) {
		this.notebookId = notebookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", text=" + text + ", lastModifiedOn=" + lastModifiedOn + "]";
	}
	
}
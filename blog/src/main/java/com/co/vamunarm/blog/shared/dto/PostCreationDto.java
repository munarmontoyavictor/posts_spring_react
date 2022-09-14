package com.co.vamunarm.blog.shared.dto;

import java.io.Serializable;

public class PostCreationDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String body;
	private long exposureId;
	private int expirationAt;
	private String userEmail;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	
	public long getExposureId() {
		return exposureId;
	}
	public void setExposureId(long exposureId) {
		this.exposureId = exposureId;
	}
	public int getExpirationAt() {
		return expirationAt;
	}
	public void setExpirationAt(int expirationAt) {
		this.expirationAt = expirationAt;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
}

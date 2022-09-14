package com.co.vamunarm.blog.models.requests;

public class PostCreateRequestModel {

	private String title;
	private String body;
	private long exposureId;
	private int expirationAt;
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
	
	
}

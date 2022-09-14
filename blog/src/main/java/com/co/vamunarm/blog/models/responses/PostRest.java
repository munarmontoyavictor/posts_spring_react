package com.co.vamunarm.blog.models.responses;

import java.util.Date;

public class PostRest {
	
private String postId;
	
	private String title;
	
	private String body;
	
	private Date expirationAt;
	
	private Date createAt;
	
	private boolean expired = false;
	
	private UserRest user;
	
	private ExposureRest exposure;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

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

	public Date getExpirationAt() {
		return expirationAt;
	}

	public void setExpirationAt(Date expirationAt) {
		this.expirationAt = expirationAt;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	
	public UserRest getUser() {
		return user;
	}

	public void setUser(UserRest user) {
		this.user = user;
	}

	public ExposureRest getExposure() {
		return exposure;
	}

	public void setExposure(ExposureRest exposure) {
		this.exposure = exposure;
	}
	

}

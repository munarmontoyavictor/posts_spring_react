package com.co.vamunarm.blog.shared.dto;

import java.io.Serializable;
import java.util.Date;

import com.co.vamunarm.blog.entities.ExposureEntity;
import com.co.vamunarm.blog.entities.UserEntity;

public class PostDto implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String postId;
	
	private String title;
	
	private String body;
	
	private Date expirationAt;
	
	private Date createAt;
	
	private UserDto user;
	
	private ExposureDto exposure;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public ExposureDto getExposure() {
		return exposure;
	}

	public void setExposure(ExposureDto exposure) {
		this.exposure = exposure;
	}


	  


}

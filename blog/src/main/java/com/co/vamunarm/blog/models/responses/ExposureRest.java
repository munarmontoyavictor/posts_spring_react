package com.co.vamunarm.blog.models.responses;

import java.util.List;

import com.co.vamunarm.blog.shared.dto.PostDto;

public class ExposureRest {
	
	private long id;
	
	private String type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}

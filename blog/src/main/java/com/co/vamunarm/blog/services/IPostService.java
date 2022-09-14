package com.co.vamunarm.blog.services;

import java.util.List;

import com.co.vamunarm.blog.shared.dto.PostCreationDto;
import com.co.vamunarm.blog.shared.dto.PostDto;

public interface IPostService {
	
	public PostDto createPost(PostCreationDto post);

	public List<PostDto> getLastPosts();

}

package com.co.vamunarm.blog.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.vamunarm.blog.models.requests.PostCreateRequestModel;
import com.co.vamunarm.blog.models.responses.PostRest;
import com.co.vamunarm.blog.services.IPostService;
import com.co.vamunarm.blog.shared.dto.PostCreationDto;
import com.co.vamunarm.blog.shared.dto.PostDto;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	IPostService postService;
	
	@Autowired
	ModelMapper mapper;

	@PostMapping
	public PostRest createPost(@RequestBody PostCreateRequestModel postModel) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getPrincipal().toString();
		PostCreationDto postCreationDto = mapper.map(postModel, PostCreationDto.class);
		postCreationDto.setUserEmail(email);
		PostDto postDto = postService.createPost(postCreationDto);
		PostRest postResponse = mapper.map(postDto, PostRest.class);
		Date nowDate = new Date(System.currentTimeMillis());
		if (postResponse.getExpirationAt().compareTo(nowDate) < 0) {
			postResponse.setExpired(true);
		}
		return postResponse;
	}
	
	@GetMapping(path = "/last")
	public List<PostRest> lastPost() {
		List<PostDto> posts = postService.getLastPosts();
		List<PostRest> postRests = new ArrayList<>();
		posts.forEach(post -> {
			PostRest postRest = mapper.map(post, PostRest.class);
			postRests.add(postRest);
		});
		return postRests;
	}

}

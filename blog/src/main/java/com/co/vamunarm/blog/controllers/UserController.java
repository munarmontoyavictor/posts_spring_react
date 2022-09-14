package com.co.vamunarm.blog.controllers;

import com.co.vamunarm.blog.models.requests.UserDetailsRequestModel;

import com.co.vamunarm.blog.models.responses.UserRest;
import com.co.vamunarm.blog.services.IUserService;
import com.co.vamunarm.blog.shared.dto.PostDto;
import com.co.vamunarm.blog.shared.dto.UserDto;
import com.co.vamunarm.blog.models.responses.PostRest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	IUserService userService;
	
	@Autowired
	ModelMapper mapper;


	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String user = authentication.getPrincipal().toString();
		UserDto userDto = userService.getUser(user);
		UserRest userRest = mapper.map(userDto, UserRest.class);
		// BeanUtils.copyProperties(userDto, userRest);
		return userRest;
	}

	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetais) {
		UserRest userResponse = new UserRest();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetais, userDto);
		UserDto newUser = userService.createUser(userDto);
		BeanUtils.copyProperties(newUser, userResponse);
		return userResponse;
	}

	@GetMapping(path = "/posts")
	public List<PostRest> getPosts() {
		List<PostRest> postRests = new ArrayList<>();
		Date nowDate = new Date(System.currentTimeMillis());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getPrincipal().toString();
		List<PostDto> posts = userService.getUserPosts(userEmail);
		posts.forEach(post -> {
			PostRest postRest = mapper.map(post, PostRest.class);
			if (postRest.getExpirationAt().compareTo(nowDate) < 0) {
				postRest.setExpired(true);
			}
			postRests.add(postRest);
		});
		return postRests;

	}

}

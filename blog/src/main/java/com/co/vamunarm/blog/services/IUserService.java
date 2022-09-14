package com.co.vamunarm.blog.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.co.vamunarm.blog.shared.dto.PostDto;
import com.co.vamunarm.blog.shared.dto.UserDto;

public interface IUserService extends UserDetailsService {
  public UserDto createUser(UserDto user);
  public UserDto getUser(String email);
  public List<PostDto> getUserPosts(String email);
}

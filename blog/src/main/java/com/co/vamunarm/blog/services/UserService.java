package com.co.vamunarm.blog.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.co.vamunarm.blog.entities.PostEntity;
import com.co.vamunarm.blog.entities.UserEntity;
import com.co.vamunarm.blog.exceptions.EmailExistException;
import com.co.vamunarm.blog.repositories.PostRepository;
import com.co.vamunarm.blog.repositories.UserRepository;
import com.co.vamunarm.blog.shared.dto.PostDto;
import com.co.vamunarm.blog.shared.dto.UserDto;

@Service
public class UserService implements IUserService{

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  PostRepository postRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Autowired
  ModelMapper mapper;
  
  
  @Override
  public UserDto createUser(UserDto user) {
    if(userRepository.findUserByEmail(user.getEmail()) != null)
       throw new EmailExistException("El correo electronico ya existe");
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);
    String encodePass = bCryptPasswordEncoder.encode(user.getPassword());
    userEntity.setEncryptedPassword(encodePass);
    UUID userId = UUID.randomUUID();
    userEntity.setUserId(userId.toString());
    UserEntity storedUserDetails = userRepository.save(userEntity);
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(storedUserDetails, userDto);
    return userDto;
  }
  
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findUserByEmail(email);
    if (userEntity == null) {
      throw new UsernameNotFoundException(email);
    }
    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
  }
  
  @Override
  public UserDto getUser(String email) {
    UserEntity userEntity = userRepository.findUserByEmail(email);
    if (userEntity == null) {
      throw new UsernameNotFoundException(email);
    }
    UserDto userToReturn = new UserDto();
    BeanUtils.copyProperties(userEntity, userToReturn);
    return userToReturn;
  }
  
	@Override
	public List<PostDto> getUserPosts(String email) {
		UserEntity userEntity = userRepository.findUserByEmail(email);
		List<PostEntity> posts = postRepository.getByUserIdOrderByCreateAtDesc(userEntity.getId());
		List<PostDto> postDtos = new ArrayList<>();
		posts.forEach(post -> {
			PostDto postDto = mapper.map(post, PostDto.class);
			postDtos.add(postDto);
		});
	  	
		return postDtos;
	}


}

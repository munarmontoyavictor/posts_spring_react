package com.co.vamunarm.blog.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.vamunarm.blog.entities.ExposureEntity;
import com.co.vamunarm.blog.entities.PostEntity;
import com.co.vamunarm.blog.entities.UserEntity;
import com.co.vamunarm.blog.repositories.ExpousureRepository;
import com.co.vamunarm.blog.repositories.PostRepository;
import com.co.vamunarm.blog.repositories.UserRepository;
import com.co.vamunarm.blog.shared.dto.PostCreationDto;
import com.co.vamunarm.blog.shared.dto.PostDto;

@Service
public class PostService implements IPostService {

	public static final int MIN_TO_MILSEC = 60000;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ExpousureRepository expousureRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public PostDto createPost(PostCreationDto post) {
		UserEntity user = userRepository.findUserByEmail(post.getUserEmail());
		ExposureEntity exposureEntity = expousureRepository.findExposureById(post.getExposureId());
		PostEntity entity = new PostEntity();
		entity.setUser(user);
		entity.setTitle(post.getTitle());
		entity.setBody(post.getBody());
		entity.setExposure(exposureEntity);
		entity.setPostId(UUID.randomUUID().toString());
		Long expirationAt = System.currentTimeMillis() + post.getExpirationAt() * MIN_TO_MILSEC;
		entity.setExpirationAt(new Date(expirationAt));
		PostEntity createdPost = postRepository.save(entity);
		PostDto postDto = mapper.map(createdPost, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getLastPosts() {
		long publicExposureId = 2;
		Date now = new Date(System.currentTimeMillis());
		List<PostEntity> postEntities = postRepository.getLastPublicPosts(publicExposureId, now);
		List<PostDto> posts = new ArrayList<>();
		postEntities.forEach(post -> {
			PostDto postDto = mapper.map(post, PostDto.class);
			posts.add(postDto);
		});
		return posts;
	}

}

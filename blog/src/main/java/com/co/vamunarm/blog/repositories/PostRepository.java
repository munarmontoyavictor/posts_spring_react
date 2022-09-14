package com.co.vamunarm.blog.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.co.vamunarm.blog.entities.PostEntity;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {

	List<PostEntity> getByUserIdOrderByCreateAtDesc(long userId);

	@Query(value = "SELECT * FROM posts p " 
	+ "WHERE p.exposure_id = :exposure AND p.expiration_at > :now "
	+ "ORDER BY p.create_at DESC LIMIT 20",
			nativeQuery = true)
	List<PostEntity> getLastPublicPosts(
			@Param("exposure") long exposureId,
			@Param("now") Date now);
}

package com.co.vamunarm.blog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.co.vamunarm.blog.entities.ExposureEntity;

@Repository
public interface ExpousureRepository extends CrudRepository<ExposureEntity, Long> {
	
	public ExposureEntity findExposureById(Long id);
}

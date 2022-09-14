package com.co.vamunarm.blog.repositories;

import com.co.vamunarm.blog.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//Permite dar funcionalidades de crud a una entidad
// sin necesidad de codigo ni consulttas
// debo indicar la entidad y el tipo de sdato de la clase primaria
//Sprint y jpa
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

  public UserEntity findUserByEmail(String email);
}

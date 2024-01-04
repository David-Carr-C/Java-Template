package com.example.repository;

import com.example.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository<T extends UserEntity> extends CrudRepository<T, Long> {

    // selecciona u de la clase UserEntity donde u.username = primer parametro
//    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
//    Optional<T> username(String username); // necesita una Query no sigue convencion

    Optional<T> findByUsername(String username); // no necesita una Query porque sigue la convencion de nombres de Spring y ya sabe que hacer
}

package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    Iterable<UserEntity> getUsers();
    Iterable<UserEntity> getUser(Long id);
    ResponseEntity<String> postUser(UserDTO userDTO);
    ResponseEntity<String> putUser(UserDTO userDTO);
    ResponseEntity<String> deleteUser(Long id);
}

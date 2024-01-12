package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import com.example.exception.UserException;

public interface IUserService {
    Iterable<UserEntity> getUsers();
    Iterable<UserEntity> getUser(Long id);
    UserEntity postUser(UserDTO userDTO);
    UserEntity putUser(Long id, UserDTO userDTO) throws UserException;
    String deleteUser(Long id);
}

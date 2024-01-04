package com.example.service.implementation;

import com.example.dto.UserDTO;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.enums.ERole;
import com.example.repository.IUserRepository;
import com.example.service.IUserService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Builder
public class UserService implements IUserService {

    @Autowired
    private IUserRepository<UserEntity> userRepository;

    @Override
    public Iterable<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<UserEntity> getUser(Long id) {
        return userRepository.findAllById(List.of(id));
    }

    @Override
    public ResponseEntity<String> postUser(UserDTO userDTO) {
        Set<RoleEntity> roles = userDTO
                .getRoles()
                .stream()
                .map(role -> RoleEntity
                        .builder()
                        .role(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity
                .builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok("Usuario creado");
    }

    @Override
    public ResponseEntity<String> putUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado");
    }
}

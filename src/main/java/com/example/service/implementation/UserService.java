package com.example.service.implementation;

import com.example.dto.UserDTO;
import com.example.dto.mapper.UserDTOMapper;
import com.example.entity.RoleEntity;
import com.example.entity.UserEntity;
import com.example.enums.ERole;
import com.example.exception.UserException;
import com.example.repository.IUserRepository;
import com.example.service.IUserService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Builder
@Slf4j
public class UserService implements IUserService {

    @Autowired
    private IUserRepository<UserEntity> userRepository;

    @Override
    public Iterable<UserEntity> getUsers() {
        log.debug("Getting all users");
        Stream<UserEntity> streamUsers = StreamSupport.stream(userRepository.findAll().spliterator(), false);
        return streamUsers
                .peek(user -> {
                    if (user.getUsername() != null) user.setUsername(user.getUsername().toUpperCase());
                    if (user.getPassword() != null) user.setPassword("**********");
                }).collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> getUser(Long id) {
        log.debug("Getting user with id: {}", id);
        return new ArrayList<>((Collection<UserEntity>) userRepository.findAllById(List.of(id)));
    }

    @Override
    public UserEntity postUser(UserDTO userDTO) {
        log.debug("Creating user: {}", userDTO);
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

        log.debug("Saving user: {}", userEntity);
        userRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public UserEntity putUser(Long id, UserDTO userDTO)  throws UserException{
        log.debug("Updating user: {}", userDTO);
        if (!userRepository.existsById(id)) throw new UserException("El usuario no existe", "No se encontro registro del usuario con id: " + id);

        UserEntity userEntity = UserDTOMapper.toUser(userDTO);

        Set<RoleEntity> roles = userDTO
                .getRoles()
                .stream()
                .map(role -> RoleEntity
                        .builder()
                        .role(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        userEntity.setRoles(roles);
        userEntity.setId(id);

        log.debug("Saving user: {}", userEntity);
        userRepository.save(userEntity);

        return userEntity;
    }

    @Override
    public String deleteUser(Long id) {
        log.debug("Deleting user with id: {}", id);
        userRepository.deleteById(id);
        return "Usuario eliminado";
    }
}

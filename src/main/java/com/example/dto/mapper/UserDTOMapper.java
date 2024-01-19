package com.example.dto.mapper;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserDTOMapper {
    public static UserDTO fromUser(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }

    public static UserEntity toUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        return userEntity;
    }
}

/**
 * Esta clase se encarga de copiar los datos de la entidad UserEntity a la clase UserDTO y viceversa.
 * Para ello, se utiliza la clase BeanUtils de Spring, que permite copiar los atributos de una clase a otra.
 * En este caso, se copian los atributos de la entidad UserEntity a la clase UserDTO y viceversa.
 */
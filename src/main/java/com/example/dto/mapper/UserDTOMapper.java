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

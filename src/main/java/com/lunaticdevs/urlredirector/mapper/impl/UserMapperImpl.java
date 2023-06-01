package com.lunaticdevs.urlredirector.mapper.impl;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.entity.User;
import com.lunaticdevs.urlredirector.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * author: Saransh Kumar
 */
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
    }
}

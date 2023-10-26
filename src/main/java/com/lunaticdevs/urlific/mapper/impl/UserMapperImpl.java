package com.lunaticdevs.urlific.mapper.impl;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.entity.User;
import com.lunaticdevs.urlific.mapper.UserMapper;
import org.springframework.stereotype.Component;

/**
 * @author Saransh Kumar
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

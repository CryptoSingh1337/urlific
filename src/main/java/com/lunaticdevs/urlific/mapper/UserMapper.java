package com.lunaticdevs.urlific.mapper;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.entity.User;

/**
 * @author Saransh Kumar
 */

public interface UserMapper {

    User userDtoToUser(UserDTO userDTO);
}

package com.lunaticdevs.urlredirector.mapper;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.entity.User;

/**
 * @author Saransh Kumar
 */

public interface UserMapper {

    User userDtoToUser(UserDTO userDTO);
}

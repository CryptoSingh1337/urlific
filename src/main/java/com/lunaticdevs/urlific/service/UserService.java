package com.lunaticdevs.urlific.service;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.entity.User;
import com.lunaticdevs.urlific.exception.UserAlreadyExistsException;

/**
 * @author Saransh Kumar
 */

public interface UserService {

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void save(UserDTO userDTO) throws UserAlreadyExistsException;

    void save(User user);
}

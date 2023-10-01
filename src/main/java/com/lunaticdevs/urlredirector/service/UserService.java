package com.lunaticdevs.urlredirector.service;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.entity.User;
import com.lunaticdevs.urlredirector.exception.UserAlreadyExistsException;

/**
 * @author Saransh Kumar
 */

public interface UserService {

    User getUserByUsername(String username);

    void save(UserDTO userDTO) throws UserAlreadyExistsException;
}

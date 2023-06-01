package com.lunaticdevs.urlredirector.service;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.entity.User;

/**
 * author: Saransh Kumar
 */
public interface UserService {

    User getUserByUsername(String username);

    void save(UserDTO userDTO);
}

package com.lunaticdevs.urlific.service;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.entity.User;

/**
 * @author Saransh Kumar
 */

public interface UserService {

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    void verifyEmail(String token);

    void save(UserDTO userDTO);

    void save(User user);
}

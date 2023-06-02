package com.lunaticdevs.urlredirector.service.impl;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.entity.Role;
import com.lunaticdevs.urlredirector.entity.User;
import com.lunaticdevs.urlredirector.exception.UserAlreadyExistsException;
import com.lunaticdevs.urlredirector.exception.UserNotFoundException;
import com.lunaticdevs.urlredirector.mapper.UserMapper;
import com.lunaticdevs.urlredirector.repository.UserRepository;
import com.lunaticdevs.urlredirector.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * author: Saransh Kumar
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmailHelper(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return findByUsernameHelper(username);
    }

    @Override
    public void save(UserDTO userDTO) throws UserAlreadyExistsException {
        log.debug("Saving the user with username: {}", userDTO.getUsername());

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        if (userOptional.isPresent()) {
            log.error("User already exists with the same username or email");
            throw new UserAlreadyExistsException();
        }

        User user = userMapper.userDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfileImage(String.format("https://avatars.dicebear.com/api/bottts/%s.svg", user.getUsername()));
        user.setIsAccountVerified(false);
        user.setAuthorities(List.of(Role.USER));
        userRepository.save(user);
    }

    private User findByEmailHelper(String email) {
        log.debug("Retrieving user with email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", email);
                    return new UserNotFoundException();
                });
    }

    private User findByUsernameHelper(String username) {
        log.debug("Retrieving user with username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found with username: {}", username);
                    return new UserNotFoundException();
                });
    }
}

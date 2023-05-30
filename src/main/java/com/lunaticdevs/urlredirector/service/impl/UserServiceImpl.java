package com.lunaticdevs.urlredirector.service.impl;

import com.lunaticdevs.urlredirector.dto.UserDTO;
import com.lunaticdevs.urlredirector.entity.User;
import com.lunaticdevs.urlredirector.exception.UserNotFoundException;
import com.lunaticdevs.urlredirector.mapper.UserMapper;
import com.lunaticdevs.urlredirector.repository.UserRepository;
import com.lunaticdevs.urlredirector.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Retrieving user with username: {}", username);
        User user = findByUsernameHelper(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getIsAccountVerified(), true, true, true,
                user.getAuthorities());
    }

    @Override
    public void save(UserDTO userDTO) {
        log.debug("Saving the user with username: {}", userDTO.getUsername());
        User user = userMapper.userDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfileImage(String.format("https://avatars.dicebear.com/api/bottts/%s.svg", user.getUsername()));
        userRepository.save(user);
    }

    private User findByUsernameHelper(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }
}

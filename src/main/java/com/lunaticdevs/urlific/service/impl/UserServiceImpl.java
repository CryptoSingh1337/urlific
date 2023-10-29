package com.lunaticdevs.urlific.service.impl;

import com.lunaticdevs.urlific.dto.UserDTO;
import com.lunaticdevs.urlific.entity.Role;
import com.lunaticdevs.urlific.entity.User;
import com.lunaticdevs.urlific.event.SendVerifyEmailEvent;
import com.lunaticdevs.urlific.exception.UserAlreadyExistsException;
import com.lunaticdevs.urlific.exception.UserNotFoundException;
import com.lunaticdevs.urlific.helper.JwtHelper;
import com.lunaticdevs.urlific.mapper.UserMapper;
import com.lunaticdevs.urlific.repository.UserRepository;
import com.lunaticdevs.urlific.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtHelper jwtHelper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return findByEmailHelper(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return findByUsernameHelper(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return findByEmailHelper(email);
    }

    @Override
    public void verifyEmail(String token) {
        String username = jwtHelper.verifyEmailVerificationToken(token);
        if (StringUtils.hasText(username)) {
            log.info("Verifying the email of user with username: {}", username);
            User user = findByUsernameHelper(username);
            user.setIsAccountVerified(true);
            userRepository.save(user);
        }
    }

    @Override
    public void save(UserDTO userDTO) {
        log.debug("Saving the user with username: {}", userDTO.getUsername());

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail());
        if (userOptional.isPresent()) {
            log.error("User already exists with the same username or email");
            throw new UserAlreadyExistsException();
        }

        User user = userMapper.userDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfileImage(String.format("https://api.dicebear.com/7.x/initials/svg?seed=%s.svg", user.getUsername()));
        user.setIsAccountVerified(false);
        user.setAuthorities(List.of(Role.USER));
        save(user);
        applicationEventMulticaster.multicastEvent(new SendVerifyEmailEvent(this,
                userDTO.getEmail(), userDTO.getUsername()));
    }

    @Override
    public void save(User user) {
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

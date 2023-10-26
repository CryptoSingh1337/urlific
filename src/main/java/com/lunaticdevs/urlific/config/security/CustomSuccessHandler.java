package com.lunaticdevs.urlific.config.security;

import com.lunaticdevs.urlific.entity.User;
import com.lunaticdevs.urlific.exception.UserNotFoundException;
import com.lunaticdevs.urlific.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.lunaticdevs.urlific.entity.Role.USER;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        if (authentication.getPrincipal() instanceof DefaultOAuth2User oAuth2User) {
            String email = oAuth2User.getAttribute("email");
            User user = null;
            try {
                user = userService.getUserByEmail(email);
            } catch (UserNotFoundException ignore) {}

            if (user == null) {
                user = User.builder()
                        .email(email)
                        .firstName(oAuth2User.getAttribute("given_name"))
                        .lastName(oAuth2User.getAttribute("family_name"))
                        .username(String.format("%s%s", oAuth2User.getAttribute("given_name"),
                                oAuth2User.getName().substring(0, 5)))
                        .email(oAuth2User.getAttribute("email"))
                        .profileImage(oAuth2User.getAttribute("picture"))
                        .authorities(List.of(USER))
                        .isAccountVerified(oAuth2User.getAttribute("email_verified"))
                        .build();
                userService.save(user);
            }
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authToken = new UsernamePasswordAuthenticationToken(user,
                    authentication.getCredentials(), user.getAuthorities());
            securityContext.setAuthentication(authToken);
        }
        response.sendRedirect("/dashboard");
    }
}

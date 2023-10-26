package com.lunaticdevs.urlific.config.credential;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Saransh Kumar
 */

@ConfigurationProperties(prefix = "spring.security.oauth2.client.registration.google")
public record GoogleCredential(@NotEmpty String clientId,
                               @NotEmpty String clientSecret,
                               @NotEmpty String redirectUri,
                               @NotEmpty List<String> scope,
                               @NotEmpty String authorizationUri,
                               @NotEmpty String tokenUri,
                               @NotEmpty String userInfoUri,
                               @NotEmpty String jwkSetUri) {
}

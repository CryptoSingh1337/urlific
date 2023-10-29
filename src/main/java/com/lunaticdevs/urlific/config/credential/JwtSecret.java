package com.lunaticdevs.urlific.config.credential;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Saransh Kumar
 */

@ConfigurationProperties(prefix = "jwt.secret")
public record JwtSecret(@NotEmpty String verifyEmail) {
}

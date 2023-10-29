package com.lunaticdevs.urlific.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lunaticdevs.urlific.config.credential.JwtSecret;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * @author Saransh Kumar
 */

@Component
public class JwtHelper {

    private final Algorithm verifyTokenAlgorithm;

    public JwtHelper(JwtSecret secret) {
        verifyTokenAlgorithm = Algorithm.HMAC256(secret.verifyEmail());
    }

    public String generateEmailVerifyToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(Instant.now()
                        .plusMillis(84600))
                .withIssuer("URLific")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(verifyTokenAlgorithm);
    }

    public String verifyEmailVerificationToken(String token) {
        return JWT.require(verifyTokenAlgorithm)
                .build()
                .verify(token)
                .getSubject();
    }
}

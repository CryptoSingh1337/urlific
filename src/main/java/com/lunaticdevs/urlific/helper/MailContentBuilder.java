package com.lunaticdevs.urlific.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Saransh Kumar
 */

@RequiredArgsConstructor
@Component
public class MailContentBuilder {

    private final TemplateEngine templateEngine;
    private final JwtHelper jwtHelper;
    @Value("${server.base.address}")
    private String SERVER_BASE_ADDRESS;
    @Value("${server.port}")
    private String PORT;
    @Value("${spring.profiles.active}")
    private String profile;

    public String getVerifyEmailContent(String username) {
        String token = jwtHelper.generateEmailVerifyToken(username);
        Context context = new Context();
        context.setVariable("token", token);
        context.setVariable("basePath", getBasePath());
        return templateEngine.process("email/template", context);
    }

    private String getBasePath() {
        if (profile.contains("prod")) {
            return SERVER_BASE_ADDRESS;
        } else {
            return SERVER_BASE_ADDRESS + ":" + PORT;
        }
    }
}

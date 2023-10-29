package com.lunaticdevs.urlific.service.impl;

import com.lunaticdevs.urlific.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendMail(String email, String text, boolean isHtml) {
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setFrom("lunaticdevs1337@gmail.com");
            helper.setTo(email);
            helper.setSubject("Verify your email address");
            helper.setText(text, isHtml);
        };
        mailSender.send(message);
        log.info("Email send to {}", email);
    }
}

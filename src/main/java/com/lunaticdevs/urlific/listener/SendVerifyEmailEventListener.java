package com.lunaticdevs.urlific.listener;

import com.lunaticdevs.urlific.event.SendVerifyEmailEvent;
import com.lunaticdevs.urlific.helper.MailContentBuilder;
import com.lunaticdevs.urlific.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Saransh Kumar
 */

@RequiredArgsConstructor
@Component
public class SendVerifyEmailEventListener implements ApplicationListener<SendVerifyEmailEvent> {

    private final MailService mailService;
    private final MailContentBuilder mailContentBuilder;

    @Override
    public void onApplicationEvent(SendVerifyEmailEvent event) {
        mailService.sendMail(event.getEmail(),
                mailContentBuilder.getVerifyEmailContent(event.getUsername()), true);
    }
}

package com.lunaticdevs.urlific.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Saransh Kumar
 */

@Getter
public class SendVerifyEmailEvent extends ApplicationEvent {

    private final String email;
    private final String username;

    public SendVerifyEmailEvent(Object source, String email, String username) {
        super(source);
        this.email = email;
        this.username = username;
    }
}

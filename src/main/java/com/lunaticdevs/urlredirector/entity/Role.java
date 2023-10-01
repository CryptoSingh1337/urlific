package com.lunaticdevs.urlredirector.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Saransh Kumar
 */

public enum Role implements GrantedAuthority {

    USER,
    MODERATOR,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

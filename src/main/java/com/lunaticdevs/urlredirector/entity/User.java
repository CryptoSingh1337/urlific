package com.lunaticdevs.urlredirector.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

/**
 * author: Saransh Kumar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImage;
    private Boolean enabled;
    private Collection<Role> authorities;
}

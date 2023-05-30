package com.lunaticdevs.urlredirector.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(name = "email_idx", unique = true)
    private String email;
    @Indexed(name = "username_idx", unique = true)
    private String username;
    private String password;
    private String profileImage;
    private Boolean isAccountVerified;
    private Collection<Role> authorities;
}

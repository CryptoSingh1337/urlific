package com.lunaticdevs.urlific.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Saransh Kumar
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "links")
public class Link {

    @Id
    private String id;
    @Indexed(name = "name_idx", unique = true)
    private String name;
    private String originalUrl;
    private String redirectUrl;
    @Indexed(name = "username_idx")
    private String username;
    @Version
    private Integer version;
}

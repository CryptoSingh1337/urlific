package com.lunaticdevs.urlific.dto;

import lombok.Data;

/**
 * @author Saransh Kumar
 */

@Data
public class LinkDTO {

    private String id;
    private String name;
    private String originalUrl;
    private String redirectUrl;
}

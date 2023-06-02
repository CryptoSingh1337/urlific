package com.lunaticdevs.urlredirector.mapper.impl;

import com.lunaticdevs.urlredirector.dto.LinkDTO;
import com.lunaticdevs.urlredirector.entity.Link;
import com.lunaticdevs.urlredirector.mapper.LinkMapper;
import org.springframework.stereotype.Component;

/**
 * author: Saransh Kumar
 */
@Component
public class LinkMapperImpl implements LinkMapper {

    @Override
    public Link linkDtoToLink(LinkDTO linkDTO) {
        return Link.builder()
                .name(linkDTO.getName())
                .redirectUrl(linkDTO.getRedirectUrl())
                .build();
    }

    @Override
    public LinkDTO linkToLinkDto(Link link) {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setId(link.getId());
        linkDTO.setName(link.getName());
        linkDTO.setOriginalUrl(link.getOriginalUrl());
        linkDTO.setRedirectUrl(link.getRedirectUrl());
        return linkDTO;
    }
}

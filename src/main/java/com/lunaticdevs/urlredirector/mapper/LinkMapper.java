package com.lunaticdevs.urlredirector.mapper;

import com.lunaticdevs.urlredirector.dto.LinkDTO;
import com.lunaticdevs.urlredirector.entity.Link;

/**
 * author: Saransh Kumar
 */
public interface LinkMapper {

    Link linkDtoToLink(LinkDTO linkDTO);

    LinkDTO linkToLinkDto(Link link);
}

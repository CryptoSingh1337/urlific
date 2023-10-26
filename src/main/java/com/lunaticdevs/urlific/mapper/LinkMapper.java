package com.lunaticdevs.urlific.mapper;

import com.lunaticdevs.urlific.dto.LinkDTO;
import com.lunaticdevs.urlific.entity.Link;

/**
 * @author Saransh Kumar
 */

public interface LinkMapper {

    Link linkDtoToLink(LinkDTO linkDTO);

    LinkDTO linkToLinkDto(Link link);
}

package com.lunaticdevs.urlific.service;

import com.lunaticdevs.urlific.dto.LinkDTO;
import com.lunaticdevs.urlific.entity.Link;

import java.util.List;

/**
 * @author Saransh Kumar
 */

public interface LinkService {

    List<LinkDTO> getAllByUsername(String username);

    Link getByUsernameAndName(String username, String name);

    void save(LinkDTO linkDTO);

    void update(LinkDTO linkDTO);

    void delete(String id);
}

package com.lunaticdevs.urlredirector.service;

import com.lunaticdevs.urlredirector.dto.LinkDTO;

import java.util.List;

/**
 * author: Saransh Kumar
 */
public interface LinkService {

    List<LinkDTO> getAllByUsername(String username);

    void save(LinkDTO linkDTO);
}

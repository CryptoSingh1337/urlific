package com.lunaticdevs.urlredirector.service.impl;

import com.lunaticdevs.urlredirector.dto.LinkDTO;
import com.lunaticdevs.urlredirector.entity.Link;
import com.lunaticdevs.urlredirector.entity.User;
import com.lunaticdevs.urlredirector.mapper.LinkMapper;
import com.lunaticdevs.urlredirector.repository.LinkRepository;
import com.lunaticdevs.urlredirector.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: Saransh Kumar
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

    @Override
    public List<LinkDTO> getAllByUsername(String username) {
        return linkRepository.findAllByUsername(username).stream()
                .map(linkMapper::linkToLinkDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(LinkDTO linkDTO) {
        log.debug("Saving the link with name: {}", linkDTO.getName());
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Link link = linkMapper.linkDtoToLink(linkDTO);
        link.setUsername(username);
        linkRepository.save(link);
    }
}

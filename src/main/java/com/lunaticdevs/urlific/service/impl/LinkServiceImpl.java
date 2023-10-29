package com.lunaticdevs.urlific.service.impl;

import com.lunaticdevs.urlific.dto.LinkDTO;
import com.lunaticdevs.urlific.entity.Link;
import com.lunaticdevs.urlific.entity.User;
import com.lunaticdevs.urlific.exception.LinkNotFoundException;
import com.lunaticdevs.urlific.mapper.LinkMapper;
import com.lunaticdevs.urlific.repository.LinkRepository;
import com.lunaticdevs.urlific.service.LinkService;
import com.lunaticdevs.urlific.utility.Cache;
import com.lunaticdevs.urlific.utility.CacheFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Saransh Kumar
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;
    private final Cache<String, Link> cache = CacheFactory.getCacheInstance(100);
    @Value("${server.base.address}")
    private String SERVER_BASE_ADDRESS;

    @Override
    public List<LinkDTO> getAllByUsername(String username) {
        log.debug("Retrieving all the link for username: {}", username);
        return linkRepository.findAllByUsername(username).stream()
                .map(linkMapper::linkToLinkDto)
                .collect(Collectors.toList());
    }

    @Override
    public Link getByUsernameAndName(String username, String name) {
        log.debug("Retrieving link with username: {} and name: {}", username, name);
        String key = String.format("%s%s", username, name);
        Link link = cache.get(key);
        if (link != null) {
            log.debug("Cache Hit - [{}]", key);
            return link;
        }
        link = linkRepository.findByUsernameAndName(username, name)
                .orElseThrow(() -> {
                    log.error("Link not found with username: {} and name: {}", username, name);
                    return new LinkNotFoundException();
                });
        cache.put(key, link);
        return cache.get(key);
    }

    @Override
    public void save(LinkDTO linkDTO) {
        log.debug("Saving the link with name: {}", linkDTO.getName());
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Link link = linkMapper.linkDtoToLink(linkDTO);
        link.setUsername(username);
        link.setOriginalUrl(String.format("%s/link/%s/%s", SERVER_BASE_ADDRESS, username, linkDTO.getName()));
        linkRepository.save(link);
    }

    @Override
    public void update(LinkDTO linkDTO) {
        log.debug("Updating the link with name: {}", linkDTO.getName());
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Link link = findByIdHelper(linkDTO.getId());
        removeFromCache(link.getUsername(), link.getName());
        if (!link.getName().equals(linkDTO.getName())) {
            link.setName(linkDTO.getName());
            link.setOriginalUrl(String.format("%s/link/%s/%s", SERVER_BASE_ADDRESS, username, linkDTO.getName()));
        }
        link.setRedirectUrl(linkDTO.getRedirectUrl());
        linkRepository.save(link);
    }

    @Override
    public void delete(String id) {
        log.debug("Deleting the link with ID: {}", id);
        Link link = findByIdHelper(id);
        removeFromCache(link.getUsername(), link.getName());
        linkRepository.deleteById(id);
    }

    private Link findByIdHelper(String id) {
        log.debug("Retrieving link with ID: {}", id);
        return linkRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Link not found with ID: {}", id);
                    return new LinkNotFoundException();
                });
    }

    private void removeFromCache(String username, String name) {
        cache.remove(String.format("%s%s", username, name));
    }
}

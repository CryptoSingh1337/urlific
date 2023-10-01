package com.lunaticdevs.urlredirector.repository;

import com.lunaticdevs.urlredirector.entity.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Saransh Kumar
 */

public interface LinkRepository extends MongoRepository<Link, String> {

    List<Link> findAllByUsername(String username);

    Optional<Link> findByUsernameAndName(String username, String name);
}

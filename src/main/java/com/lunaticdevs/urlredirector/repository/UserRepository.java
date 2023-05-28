package com.lunaticdevs.urlredirector.repository;

import com.lunaticdevs.urlredirector.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * author: Saransh Kumar
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

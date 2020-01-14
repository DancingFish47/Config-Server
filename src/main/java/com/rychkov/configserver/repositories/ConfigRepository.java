package com.rychkov.configserver.repositories;

import com.rychkov.configserver.entities.Config;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConfigRepository extends MongoRepository<Config, Integer> {
    Optional<Config> findFirstByOrderByVersionDesc();
}

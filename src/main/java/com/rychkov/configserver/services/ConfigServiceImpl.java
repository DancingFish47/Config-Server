package com.rychkov.configserver.services;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.exceptions.ConfigException;
import com.rychkov.configserver.entities.Config;
import com.rychkov.configserver.repositories.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final ConfigRepository configRepository;
    private Cache<String, Config> dbCache;
    private Cache<String, Config> gitCache;

    @Override
    public ConfigDto getCurrentDbConfig(String configId) {
        if (dbCache != null) {
            Config config = dbCache.getIfPresent(configId);
            if (config != null) {
                return ConfigDto.builder().error(false).config(config).build();
            } else {
                throw new ConfigException("Cached db config not found!");
            }
        } else throw new ConfigException("Cached db config not found!");
    }

    @Override
    public ConfigDto getNewDbConfig(String configId) {
        Optional<Config> optionalConfig = configRepository.findByIdOrderByConfigVersionDesc(configId);
        if (optionalConfig.isPresent()) {
            saveDbCache(optionalConfig.get());
            return ConfigDto.builder().config(optionalConfig.get()).error(false).build();
        } else throw new ConfigException("Db config not found!");
    }


    private void saveDbCache(Config config) {
        if (dbCache == null) {
            dbCache = Caffeine.newBuilder()
                    .expireAfterWrite(1, TimeUnit.MINUTES)
                    .maximumSize(100)
                    .build();
        }
        dbCache.put(config.getId(), config);
    }

}

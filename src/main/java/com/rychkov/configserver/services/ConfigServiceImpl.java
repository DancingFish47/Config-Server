package com.rychkov.configserver.services;

import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.exceptions.ConfigException;
import com.rychkov.configserver.entities.Config;
import com.rychkov.configserver.repositories.ConfigRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;
    private Config cachedDbConfig;
    private String cachedGitConfig;

    @Override
    public ConfigDto getCurrentConfig() {
        if (cachedDbConfig != null) return ConfigDto.builder().config(cachedDbConfig).error(false).build();
        else throw new ConfigException("Cached config not found!");
    }

    @Override
    public ConfigDto getNewDbConfig() {
        Optional<Config> optionalConfig = configRepository.findFirstByOrderByVersionDesc();
        if (optionalConfig.isPresent()) {
            cachedDbConfig = optionalConfig.get();
            return ConfigDto.builder().config(cachedDbConfig).error(false).build();
        } else throw new ConfigException("Db config not found!");
    }

    @Override
    public String getCurrentGitConfig() {
        if (cachedGitConfig == null) throw new ConfigException("Git config not found!");
        else return cachedGitConfig;
    }

    @Override
    public void cacheGitConfig(String config) {
        cachedGitConfig = config;
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void clearCachedConfig() {
        cachedDbConfig = null;
        cachedGitConfig = null;
    }
}

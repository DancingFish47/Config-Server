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
    private Config cachedConfig;

    @Override
    public ConfigDto getCurrentConfig() {
        if (cachedConfig != null) return ConfigDto.builder().config(cachedConfig).error(false).build() ;
        else throw new ConfigException("Cached config not found!");
    }

    @Override
    public ConfigDto getNewDbConfig() {
        Optional<Config> optionalConfig = configRepository.findFirstByOrderByVersionDesc();
        if (optionalConfig.isPresent()){
            cachedConfig = optionalConfig.get();
            return ConfigDto.builder().config(cachedConfig).error(false).build();
        }
        else throw new ConfigException("Db config not found!");
    }

    @Override
    public ConfigDto getNewGitConfig() {
        return null;
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void clearCachedConfig(){
        cachedConfig = null;
    }
}

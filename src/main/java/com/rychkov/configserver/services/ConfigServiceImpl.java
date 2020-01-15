package com.rychkov.configserver.services;

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

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {
    private final ConfigRepository configRepository;
    private Config cachedDbConfig;
    private Config cachedGitConfig;
    @Autowired
    private RestTemplate restTemplate;

    private final static String GIT_CONFIG_URL = "https://raw.githubusercontent.com/DancingFish47/Config-Server/master/config.txt";

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restClientTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Override
    public ConfigDto getCurrentDbConfig(String configId) {
        if (cachedDbConfig != null) return ConfigDto.builder().config(cachedDbConfig).error(false).build();
        else throw new ConfigException("Cached db config not found!");
    }

    @Override
    public ConfigDto getNewDbConfig(String configId) {
        Optional<Config> optionalConfig = configRepository.findByIdOrderByConfigVersionDesc(configId);
        if (optionalConfig.isPresent()) {
            cachedDbConfig = optionalConfig.get();
            return ConfigDto.builder().config(cachedDbConfig).error(false).build();
        } else throw new ConfigException("Db config not found!");
    }

    @Override
    public ConfigDto getCurrentGitConfig() {
        if (cachedGitConfig != null) return ConfigDto.builder().config(cachedGitConfig).error(false).build();
        else throw new ConfigException("Cached git config not found!");
    }

    @Override
    public ConfigDto getNewGitConfig() {
        String config = restTemplate.getForObject(GIT_CONFIG_URL, String.class);
        return ConfigDto.builder().error(false).message(config).build();
    }

    @Scheduled(cron = "${scheduled.cron}")
    public void clearCachedConfig() {
        cachedDbConfig = null;
        cachedGitConfig = null;
    }
}

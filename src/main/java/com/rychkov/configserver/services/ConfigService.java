package com.rychkov.configserver.services;

import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.entities.Config;

public interface ConfigService {
    ConfigDto getCurrentConfig();
    ConfigDto getNewDbConfig();
    String getCurrentGitConfig();
    void cacheGitConfig(String config);
}

package com.rychkov.configserver.services;

import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.entities.Config;

public interface ConfigService {
    ConfigDto getCurrentDbConfig(String configId);

    ConfigDto getNewDbConfig(String configId);

    ConfigDto getCurrentGitConfig();

    ConfigDto getNewGitConfig();
}

package com.rychkov.configserver.services;

import com.rychkov.configserver.entities.Config;

public interface ConfigService {
    Config getCurrentConfig();
    Config getNewDbConfig();
    Config getNewGitConfig();
}

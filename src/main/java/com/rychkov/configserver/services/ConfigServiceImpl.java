package com.rychkov.configserver.services;

import com.rychkov.configserver.entities.Config;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {
    @Override
    public Config getCurrentConfig() {
        return null;
    }

    @Override
    public Config getNewDbConfig() {
        return null;
    }

    @Override
    public Config getNewGitConfig() {
        return null;
    }
}

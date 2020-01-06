package com.rychkov.configserver.dtos;

import com.rychkov.configserver.entities.Config;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfigDto {
    private Config config;
    private boolean error;
    private String message;
}

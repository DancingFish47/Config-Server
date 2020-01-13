package com.rychkov.configserver.controllers;

import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.services.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainPageController {

    private final ConfigService configService;

    @GetMapping(value = "/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping(value = "/getCurrentConfig", method = RequestMethod.POST)
    @ResponseBody
    public ConfigDto getCurrentConfig() {
        return configService.getCurrentConfig();
    }

    @RequestMapping(value = "/getNewDbConfig", method = RequestMethod.POST)
    @ResponseBody
    public ConfigDto getNewDbConfig() {
        return configService.getNewDbConfig();
    }

    @RequestMapping(value = "/cacheGitConfig", method = RequestMethod.POST)
    @ResponseBody
    public void cacheGitConfig(@RequestBody String config) {
        configService.cacheGitConfig(config);
    }

    @RequestMapping(value = "/getCurrentGitConfig", method = RequestMethod.POST)
    @ResponseBody
    public String getCurrentGitConfig() {
        return configService.getCurrentGitConfig();
    }
}

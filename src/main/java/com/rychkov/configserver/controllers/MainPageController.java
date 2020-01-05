package com.rychkov.configserver.controllers;

import com.rychkov.configserver.entities.Config;
import com.rychkov.configserver.services.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainPageController {

    private final ConfigService configService;

    @GetMapping(value = "/")
    public String mainPage(){
        return "index";
    }

    @RequestMapping(value = "/getCurrentConfig", method = RequestMethod.POST)
    @ResponseBody
    public Config getCurrentConfig(){
        return configService.getCurrentConfig();
    }

    @RequestMapping(value = "/getNewDbConfig", method = RequestMethod.POST)
    @ResponseBody
    public Config getNewDbConfig(){
        return configService.getNewDbConfig();
    }

    @RequestMapping(value = "/getNewGitConfig", method = RequestMethod.POST)
    @ResponseBody
    public Config getNewGitConfig(){
        return configService.getNewGitConfig();
    }
}

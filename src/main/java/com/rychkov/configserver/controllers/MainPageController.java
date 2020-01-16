package com.rychkov.configserver.controllers;

import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.repositories.ConfigRepository;
import com.rychkov.configserver.services.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainPageController {

    private final ConfigService configService;
    private final ConfigRepository configRepository;

    @GetMapping(value = "/")
    public String mainPage(Model model) {
        model.addAttribute("configs", configRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/getCurrentDbConfig", method = RequestMethod.POST)
    @ResponseBody
    public ConfigDto getCurrentConfig(@RequestBody String configId) {
        return configService.getCurrentDbConfig(configId);
    }

    @RequestMapping(value = "/getNewDbConfig", method = RequestMethod.POST)
    @ResponseBody
    public ConfigDto getNewDbConfig(@RequestBody String configId) {
        return configService.getNewDbConfig(configId);
    }
    
}

package com.rychkov.configserver.controllers;

import com.rychkov.configserver.dtos.ConfigDto;
import com.rychkov.configserver.exceptions.ConfigException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
@ControllerAdvice
class ExceptionHandlerController {

    @ExceptionHandler(ConfigException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ConfigDto handleException(ConfigException e) {
        return ConfigDto.builder().error(true).message(e.getMessage()).build();
    }
}
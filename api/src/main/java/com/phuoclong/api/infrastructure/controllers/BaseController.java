package com.phuoclong.api.infrastructure.controllers;


import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;

import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BaseController {


    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    protected String getAccountIdFromToken(){
        var token = "asdsadkfvlcklxjfiojckl.xnjkxnlvkjlnsdlkjnczxkj.dnfkslcnxzl";
        return token;
    }
}

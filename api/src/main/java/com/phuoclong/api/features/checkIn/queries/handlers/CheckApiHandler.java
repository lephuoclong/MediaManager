package com.phuoclong.api.features.checkIn.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.checkIn.queries.CheckApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class CheckApiHandler implements Command.Handler<CheckApi, ResponseEntity<String>>{
    @Override
    public ResponseEntity<String> handle(CheckApi checkApi) {
        var result = checkApi.getAccountId();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}

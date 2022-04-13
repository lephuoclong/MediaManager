package com.phuoclong.api.features.checkIn.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.checkIn.queries.CheckApi;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Component("CheckApiHandler")
public class CheckApiHandler implements Command.Handler<CheckApi, ResponseEntity<String>>{
    @Override
    public ResponseEntity<String> handle(CheckApi checkApi) {
        var result = checkApi.getAccountId()+" 123123123";
        return ResponseEntity.ok(result);
    }
}

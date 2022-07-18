package com.phuoclong.api.features.checkIn;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.checkIn.commands.CheckApi;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("check-in")
public class CheckInController extends BaseController {

    public CheckInController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @GetMapping()
    public ResponseEntity<?> check(){
        var command = new CheckApi();
        return handleWithResponseMessage(command);
    }
}

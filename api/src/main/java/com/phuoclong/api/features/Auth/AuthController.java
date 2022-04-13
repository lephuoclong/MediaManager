package com.phuoclong.api.features.Auth;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.Auth.command.LoginAccount;
import com.phuoclong.api.features.Auth.command.RegisterAccount;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {

    public AuthController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@Valid @RequestBody RegisterAccount command){
        return handleWithResponseMessage(command);
    }

    @PostMapping("/login")
    public ResponseEntity<?> registerGetAccount(@Valid @RequestBody LoginAccount command){
        return handleWithResponseMessage(command);
    }

}

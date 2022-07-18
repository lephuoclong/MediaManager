package com.phuoclong.api.features.account;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.account.commands.ChangeAccountInfo;
import com.phuoclong.api.features.account.queries.GetAccountInfo;
import com.phuoclong.api.features.account.queries.GetListAccount;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("accounts")
public class AccountController extends BaseController {

    public AccountController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @GetMapping()
    public ResponseEntity<?> getListAccount(@RequestParam String keyword){
        var query =GetListAccount.of(keyword);
        return handleWithResponse(query);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getBaseAccountInfo(){
        var query = new GetAccountInfo();
        return handleWithResponseMessage(query);
    }

    @PostMapping("/change-info")
    public ResponseEntity<?> changeAccountInfo(@Valid @RequestBody ChangeAccountInfo command){
        return handleWithResponseMessage(command);
    }
}

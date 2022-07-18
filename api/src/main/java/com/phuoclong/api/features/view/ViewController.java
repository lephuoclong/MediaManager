package com.phuoclong.api.features.view;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.features.view.queries.GetFileContent;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("views")
public class ViewController extends BaseController {
    public ViewController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @GetMapping("file/{fileId}")
    ResponseEntity<?> viewPhoto(@PathVariable UUID fileId){
        var query = GetFileContent.of(fileId);
        return handleWithResponse(query);
    }
}

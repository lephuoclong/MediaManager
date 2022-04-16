package com.phuoclong.api.features.documents;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("documents")
public class DocumentController extends BaseController {
    public DocumentController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getListDocumentByCategoryName(@Valid
                                                           @PathVariable String categoryName){
        return null;
    }


}

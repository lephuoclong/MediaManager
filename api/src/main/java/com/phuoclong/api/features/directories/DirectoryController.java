package com.phuoclong.api.features.directories;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.directories.commands.CreateDirectory;
import com.phuoclong.api.features.directories.commands.UpdateDirectory;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("directories")
public class DirectoryController extends BaseController {
    public DirectoryController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @PostMapping()
    public ResponseEntity<?> createDirectory(@Valid @RequestBody CreateDirectory command){
        return handleWithResponseMessage(command);
    }

    @PutMapping("/{directoryId}")
    public ResponseEntity<?> updateDirectory(@Valid @RequestBody UpdateDirectory command, @PathVariable UUID directoryId){
        command.setDirectoryId(directoryId);
        return handleWithResponseMessage(command);
    }
}

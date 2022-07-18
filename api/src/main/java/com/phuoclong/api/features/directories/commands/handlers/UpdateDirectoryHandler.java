package com.phuoclong.api.features.directories.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.commands.UpdateDirectory;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("UpdateDirectoryHandler")
public class UpdateDirectoryHandler implements Command.Handler<UpdateDirectory, ResponseMessageOf<String>> {

    private final DirectoryRepository directoryRepository;

    @Override
    public ResponseMessageOf<String> handle(UpdateDirectory command) {

        var directoryUpdate = directoryRepository.findByIdAndAccountId(command.getDirectoryId(),command.getAccountId());

        if(directoryUpdate.isEmpty()){
            return ResponseMessageOf.ofBadRequest(ResourceMessage.NOTFOUND_DIRECTORY,
                    Map.of(UpdateDirectory.Fields.directoryId, ResourceMessage.NOTFOUND_DIRECTORY) );
        }

        var newDirectory = directoryUpdate.get();

        newDirectory.setName(command.getName());

        directoryRepository.saveAndFlush(newDirectory);

        return ResponseMessageOf.of(HttpStatus.ACCEPTED, ResourceMessage.UPDATE_DIRECTORY_SUCCESS);
    }
}

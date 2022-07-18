package com.phuoclong.api.features.directories.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.commands.CreateDirectory;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("CreateDirectoryHandler")
public class CreateDirectoryHandler implements Command.Handler<CreateDirectory, ResponseMessageOf<String>> {

    private final DirectoryRepository directoryRepository;

    @Override
    public ResponseMessageOf<String> handle(CreateDirectory command) {

        var parentDirectory = directoryRepository.findByIdAndLevel(command.getParentId(), command.getLevel());

        if(parentDirectory.isEmpty()){
            return ResponseMessageOf.ofBadRequest(ResourceMessage.NOTFOUND_PARENT_DIRECTORY,
                    Map.of(CreateDirectory.Fields.parentId, ResourceMessage.NOTFOUND_PARENT_DIRECTORY));
        }

        var existsDirectoryName = directoryRepository.existsByNameAndParentId(command.getName(), command.getParentId());

        if (Boolean.TRUE.equals(existsDirectoryName)){

            return ResponseMessageOf.ofBadRequest(ResourceMessage.EXISTS_DIRECTORY,
                    Map.of(CreateDirectory.Fields.name, ResourceMessage.EXISTS_DIRECTORY));

        }

        var directory = new DirectoryEntity();

        directory.setLevel(command.getLevel());
        directory.setName(command.getName());
        directory.setParentId(command.getParentId());
        directory.setAccountId(command.getAccountId());

        directoryRepository.saveAndFlush(directory);

        return ResponseMessageOf.of(HttpStatus.ACCEPTED);
    }
}

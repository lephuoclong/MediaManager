package com.phuoclong.api.features.directories.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.commands.UpdateDirectory;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.resouce.ResponseConstant;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("UpdateDirectoryHandler")
public class UpdateDirectoryHandler implements Command.Handler<UpdateDirectory, ResponseMessageOf<DirectoryEntity>> {

    private final DirectoryRepository directoryRepository;

    @Override
    public ResponseMessageOf<DirectoryEntity> handle(UpdateDirectory command) {

        var directoryUpdate = directoryRepository.findById(command.getDirectoryId());

        if(directoryUpdate.isEmpty()){
            return ResponseMessageOf.ofBadRequest(ResponseConstant.NOTFOUND_DIRECTORY,
                    Map.of(UpdateDirectory.Fields.directoryId, ResponseConstant.NOTFOUND_DIRECTORY) );
        }

        var newDirectory = directoryUpdate.get();

        newDirectory.setName(command.getName());

        directoryRepository.saveAndFlush(newDirectory);

        return ResponseMessageOf.of(HttpStatus.ACCEPTED, ResponseConstant.UPDATE_DIRECTORY_SUCCESS,
                Map.of(UpdateDirectory.Fields.name, ResponseConstant.UPDATE_DIRECTORY_SUCCESS), newDirectory);
    }
}

package com.phuoclong.api.features.directories.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.commands.DeleteDirectory;
import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.repositories.ContentRepository;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Component("DeleteDirectoryHandler")
public class DeleteDirectoryHandler implements Command.Handler<DeleteDirectory, ResponseMessageOf<String>> {

    private final DirectoryRepository directoryRepository;
    private final FileRepository fileRepository;
    private final ContentRepository contentRepository;

    @Override
    public ResponseMessageOf<String> handle(DeleteDirectory command) {

        var directory = directoryRepository.findByIdAndAccountId(command.getDirectoryId(), command.getAccountId());

        if (directory.isEmpty())
        {
            return ResponseMessageOf.ofBadRequest(ResourceMessage.FOLDER_NOT_MATCH_IN_YOUR_ACCOUNT,
                    Map.of(DeleteDirectory.Fields.directoryId, ResourceMessage.FOLDER_NOT_MATCH_IN_YOUR_ACCOUNT));
        }

        if(directory.get().getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)){
            return ResponseMessageOf.ofBadRequest(ResourceMessage.CANNOT_DELETE_THIS_FOLDER,
                    Map.of(DeleteDirectory.Fields.directoryId, ResourceMessage.CANNOT_DELETE_THIS_FOLDER));
        }

        var listDeleteItems = directoryRepository.getListDeleteItems(command.getDirectoryId().toString());
        deleteContent(listDeleteItems);
            return ResponseMessageOf.of(HttpStatus.ACCEPTED, directory.get().getParentId().toString());
    }

    private void deleteContent(Collection<FolderTrees> listDeleteItems) {
        listDeleteItems.forEach(item -> {
            switch (item.getName())
            {
                case "folder":
                    directoryRepository.deleteById(UUID.fromString(item.getId()));
                 break;
                case "file":
                    fileRepository.deleteById(UUID.fromString(item.getId()));
                    break;
                case "tent":
                    contentRepository.deleteById(UUID.fromString(item.getId()));
                    break;
                default:
                    break;
            }
        });
    }
}

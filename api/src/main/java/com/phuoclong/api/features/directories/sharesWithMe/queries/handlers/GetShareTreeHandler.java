package com.phuoclong.api.features.directories.sharesWithMe.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.features.directories.sharesWithMe.queries.GetShareTree;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@Component("GetShareTreeHandler")
public class GetShareTreeHandler implements Command.Handler<GetShareTree, ResponseMessageOf<Collection<FolderTrees>>> {

    private DirectoryRepository directoryRepository;

    @Override
    public ResponseMessageOf<Collection<FolderTrees>> handle(GetShareTree query) {
        var rootFolder = directoryRepository.findById(query.getDirectoryId());

        if (rootFolder.isEmpty()){
            return ResponseMessageOf.of(HttpStatus.NOT_FOUND);
        }


        if (rootFolder.get().getParentId().equals(UUID.fromString(DirectoryConstant.ROOT_FOLDER))) {
            var rootFolderTree= directoryRepository.getFolderTree(query.getDirectoryId().toString());
            return ResponseMessageOf.of(HttpStatus.OK, rootFolderTree);
        }

        var folderTrees = directoryRepository.getShareFolderTree(query.getDirectoryId().toString(), query.getAccountId().toString());

        return ResponseMessageOf.of(HttpStatus.OK, folderTrees);
    }
}

package com.phuoclong.api.features.directories.favorite.queries.handler;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.favorite.queries.GetFavoriteTree;
import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component("GetFavoriteTreeHandler")
public class GetFavoriteTreeHandler implements Command.Handler<GetFavoriteTree, ResponseEntity<Collection<FolderTrees>>> {

    private DirectoryRepository directoryRepository;

    @Override
    public ResponseEntity<Collection<FolderTrees>> handle(GetFavoriteTree query) {

        var rootFolder = directoryRepository.findById(query.getDirectoryId());

        if (rootFolder.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (rootFolder.get().getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)) {
            var rootFolderTree= directoryRepository.getFolderTree(query.getDirectoryId().toString());
            return ResponseEntity.ok(rootFolderTree);
        }

        var folderTrees = directoryRepository.getFavoriteFolderTree(query.getDirectoryId().toString(), query.getAccountId().toString());

        if (folderTrees.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok( folderTrees);
    }
}

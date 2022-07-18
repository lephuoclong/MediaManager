package com.phuoclong.api.features.directories.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.queries.GetFolderTree;
import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component("GetFolderTreeHandler")
public class GetFolderTreeHandler implements Command.Handler<GetFolderTree, ResponseEntity<Collection<FolderTrees>>> {

    private final DirectoryRepository directoryRepository;

    @Override
    public ResponseEntity<Collection<FolderTrees>> handle(GetFolderTree query) {

        var checkFolderIsRootChild = directoryRepository.findById(query.getSelectedId());

        if (checkFolderIsRootChild.isEmpty() ){
            return ResponseEntity.notFound().build();
        }

        if (!checkFolderIsRootChild.get().getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)){
            var selectedFolder = directoryRepository.findByIdAndAccountId(query.getSelectedId(),query.getAccountId());
            if(selectedFolder.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }

        var result = directoryRepository.getFolderTree(query.getSelectedId().toString());

        if(result.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(result);
    }
}

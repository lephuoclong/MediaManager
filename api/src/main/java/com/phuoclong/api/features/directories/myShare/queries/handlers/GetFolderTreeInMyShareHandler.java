package com.phuoclong.api.features.directories.myShare.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.myShare.queries.GetFolderTreeInMyShare;
import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.ShareRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component("GetFolderTreeInMyShareHandler")
public class GetFolderTreeInMyShareHandler implements Command.Handler<GetFolderTreeInMyShare, ResponseEntity<Collection<FolderTrees>>> {

    private DirectoryRepository directoryRepository;
    private ShareRepository shareRepository;

    @Override
    public ResponseEntity<Collection<FolderTrees>> handle(GetFolderTreeInMyShare query) {



       var folder = directoryRepository.findById(query.getDirectoryId());

       if (folder.isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       if (!folder.get().getCreatedBy().equals(query.getAccountId().toString())) {
           return ResponseEntity.badRequest().build();
       }

       var share = shareRepository.findByFileIdAndShareName(query.getSelectId(), FavoriteShareConstants.FOLDER);

       if(share.isEmpty()){
           return ResponseEntity.noContent().build();
       }

       var folderTree = directoryRepository.getFolderTreeInMyShare(query.getDirectoryId().toString(), query.getSelectId().toString());

       if (folderTree.isEmpty()){
           return ResponseEntity.noContent().build();
       }

        if (query.getSelectId().equals(query.getDirectoryId())){
            var folderTreeLast = folderTree.stream().filter(i->i.getId().equals(query.getDirectoryId().toString()));

            return ResponseEntity.ok(folderTreeLast.collect(Collectors.toUnmodifiableList()));
        }

        return ResponseEntity.ok(folderTree);
    }
}

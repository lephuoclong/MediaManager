package com.phuoclong.api.features.files.myShare.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.myShare.queries.GetListFileInMyShare;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.models.MyFileShare;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component("GetListFileInMyShareHandler")
public class GetListFileInMyShareHandler implements Command.Handler<GetListFileInMyShare, ResponseEntity<Collection<MyFileShare>>> {
    private DirectoryRepository directoryRepository;
    private FileRepository fileRepository;
    @Override
    public ResponseEntity<Collection<MyFileShare>> handle(GetListFileInMyShare query) {

        var rootFolder = directoryRepository.findById(query.getParentId());

        if(rootFolder.isEmpty() || !rootFolder.get().getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)){
            return ResponseEntity.noContent().build();
        }

        var level = rootFolder.get().getLevel();

        var listFileInMyShare = fileRepository.getListFileInMyShare(query.getAccountId().toString(), FavoriteShareConstants.FILE, level);

        if (listFileInMyShare.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(listFileInMyShare);
    }
}

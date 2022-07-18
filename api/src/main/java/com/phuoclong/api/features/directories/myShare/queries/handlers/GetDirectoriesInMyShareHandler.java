package com.phuoclong.api.features.directories.myShare.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.myShare.queries.GetDirectoriesInMyShare;
import com.phuoclong.api.infrastructure.models.MyFolderShare;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component("GetDirectoriesInMyShareHandler")
@AllArgsConstructor
public class GetDirectoriesInMyShareHandler implements Command.Handler<GetDirectoriesInMyShare, ResponseMessageOf<Collection<MyFolderShare>>> {

    private DirectoryRepository directoryRepository;

    @Override
    public ResponseMessageOf<Collection<MyFolderShare>> handle(GetDirectoriesInMyShare query) {

        var parent = directoryRepository.findById(query.getParentId());

        if(parent.isEmpty()){
            return ResponseMessageOf.ofBadRequest("This folder is not shared yet", Map.of());
        }
        var level = parent.get().getLevel();

        var folders = directoryRepository.getFolderInShareByAccount(query.getAccountId().toString(), level);

        if (folders.isEmpty()) {
            return ResponseMessageOf.of(HttpStatus.NO_CONTENT);
        }
        return ResponseMessageOf.of(HttpStatus.OK, folders);
    }
}

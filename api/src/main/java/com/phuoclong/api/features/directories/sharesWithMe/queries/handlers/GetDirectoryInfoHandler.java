package com.phuoclong.api.features.directories.sharesWithMe.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.sharesWithMe.queries.GetDirectoryInfo;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetDirectoryInfoHandler")
public class GetDirectoryInfoHandler implements Command.Handler<GetDirectoryInfo, ResponseEntity<DirectoryEntity>> {
    private DirectoryRepository directoryRepository;
    @Override
    public ResponseEntity<DirectoryEntity> handle(GetDirectoryInfo query) {

        var directory = directoryRepository.findById(query.getDirectoryId());
        if(directory.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (directory.get().getCreatedBy().equals(query.getAccountId().toString())){
            return ResponseEntity.ok(directory.get());
        }

        return ResponseEntity.noContent().build();
    }
}

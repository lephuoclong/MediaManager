package com.phuoclong.api.features.directories.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.queries.GetListDirectory;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetListDirectoryHandler")
public class GetListDirectoryHandler implements Command.Handler<GetListDirectory, ResponseEntity<PageResultOf<DirectoryEntity>>> {

    private final DirectoryRepository directoryRepository;

    @Override
    public ResponseEntity<PageResultOf<DirectoryEntity>> handle(GetListDirectory query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.ASC, "createdDate"));

        if(query.getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)){
            var listRootFolder = directoryRepository.findAllDirectoriesByParentId(query.getParentId(),pageable);
            var resultRootFolder = PageResultOf.of(listRootFolder.getContent(),
                    query.getPage(),
                    listRootFolder.getTotalElements(),
                    listRootFolder.getTotalPages());
            return ResponseEntity.ok(resultRootFolder);
        }

        var directories = directoryRepository.findAllDirectoriesByParentIdAndAccountId(query.getParentId(),query.getAccountId(), pageable);

        if(directories.isEmpty() || directories.get().findFirst().isEmpty()){
            return ResponseEntity.noContent().build();
        }

        var result = PageResultOf.of(directories.getContent(),
                query.getPage(),
                directories.getTotalElements(),
                directories.getTotalPages());

        return ResponseEntity.ok(result);
    }
}

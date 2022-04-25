package com.phuoclong.api.features.directories.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.queries.GetListDirectory;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
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

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));

        var directories = directoryRepository.findAllDirectoriesByParentId(query.getParentId(), pageable);

        if(directories.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        var result = PageResultOf.of(directories.getContent(),
                query.getPage(),
                directories.getTotalElements(),
                directories.getTotalPages());

        return ResponseEntity.ok(result);
    }
}

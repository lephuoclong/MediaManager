package com.phuoclong.api.features.files.query.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.query.GetListFileInDirectory;
import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetListFileInDirectoryHandler")
public class GetListFileInDirectoryHandler implements Command.Handler<GetListFileInDirectory,ResponseEntity<PageResultOf<FileEntity>>> {

    private final DirectoryRepository directoryRepository;
    private final FileRepository fileRepository;

    @Override
    public ResponseEntity<PageResultOf<FileEntity>> handle(GetListFileInDirectory query) {

        var directory = directoryRepository.findById(query.getDirectoryId());

        if(directory.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.ASC, "createdDate"));

        var listFile = fileRepository.findAllByDirectoryIdAndAccountId(query.getDirectoryId(),query.getAccountId(), pageable);

        var result = PageResultOf.of(listFile.getContent(),
                query.getPage(), listFile.getTotalElements(), listFile.getTotalPages());

        return ResponseEntity.ok(result);
    }
}

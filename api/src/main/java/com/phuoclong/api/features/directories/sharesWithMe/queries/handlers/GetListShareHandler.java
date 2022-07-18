package com.phuoclong.api.features.directories.sharesWithMe.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.sharesWithMe.queries.GetListShare;
import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetListShareHandler")
public class GetListShareHandler implements Command.Handler<GetListShare, ResponseEntity<PageResultOf<DirectoryEntity>>> {

    private DirectoryRepository directoryRepository;

    @Override
    public ResponseEntity<PageResultOf<DirectoryEntity>> handle(GetListShare query) {
        var directoryShare = directoryRepository.findById(query.getDirectoryId());

        if(directoryShare.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var directoryEntity = directoryShare.get();

        if (directoryEntity.getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)) {
            Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "d.name"));
            var listShare = directoryRepository.findListShareWhenParentIdIsRoot(query.getAccountId(),
                    directoryEntity.getLevel(), FavoriteShareConstants.FOLDER, pageable);
            var result =PageResultOf.of(listShare.getContent(), query.getPage(),
                    listShare.getTotalElements(), listShare.getTotalPages());
            return ResponseEntity.ok(result);
        }

        var folderShareContainDirectory = directoryRepository.getFolderShareContainDirectory(query.getDirectoryId().toString(),
                query.getAccountId().toString(), FavoriteShareConstants.FOLDER);
        if(folderShareContainDirectory.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "name"));
        var directories = directoryRepository.findAllDirectoriesByParentId(query.getDirectoryId(), pageable);

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

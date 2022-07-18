package com.phuoclong.api.features.directories.favorite.queries.handler;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.favorite.queries.GetListFavorite;
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
@Component("GetListFavoriteHandler")
public class GetListFavoriteHandler implements Command.Handler<GetListFavorite, ResponseEntity<PageResultOf<DirectoryEntity>>> {

    private DirectoryRepository directoryRepository;

    @Override
    public ResponseEntity<PageResultOf<DirectoryEntity>> handle(GetListFavorite query) {

        var directoryFavorite = directoryRepository.findById(query.getDirectoryId());

        if(directoryFavorite.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var directoryEntity = directoryFavorite.get();

        if (directoryEntity.getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)) {
            Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "d.name"));

            var listFavorite = directoryRepository.findListFavoriteWhenParentIdIsRoot(query.getAccountId(),
                    directoryEntity.getLevel(), FavoriteShareConstants.FOLDER, pageable);

            var result =PageResultOf.of(listFavorite.getContent(), query.getPage(),
                    listFavorite.getTotalElements(), listFavorite.getTotalPages());
            return ResponseEntity.ok(result);
        }

        var folderFavoriteContainDirectory = directoryRepository.getFolderFavoriteContainDirectory(query.getDirectoryId().toString(),
                query.getAccountId().toString(), FavoriteShareConstants.FOLDER);
        if (folderFavoriteContainDirectory.isEmpty()) {
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

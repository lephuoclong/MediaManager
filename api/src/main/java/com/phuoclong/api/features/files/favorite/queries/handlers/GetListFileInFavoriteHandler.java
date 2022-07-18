package com.phuoclong.api.features.files.favorite.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.favorite.queries.GetListFileInFavorite;
import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import com.phuoclong.api.infrastructure.services.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("GetListFileInFavoriteHandler")
public class GetListFileInFavoriteHandler implements Command.Handler<GetListFileInFavorite, ResponseMessageOf<PageResultOf<FileEntity>>> {

    private DirectoryRepository directoryRepository;
    private FileRepository fileRepository;
    private AppUtil appUtil;

    @Override
    public ResponseMessageOf<PageResultOf<FileEntity>> handle(GetListFileInFavorite query) {

        var directoryById = directoryRepository.findById(query.getDirectoryId());

        if(directoryById.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Folder not found!", Map.of());
        }

        var directory = directoryById.get();
        var typeOfFile = appUtil.getFileTypeFromLevel(directory.getLevel());

        if(directory.getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)) {
            Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC,"f.name"));
            var listFileInRootFolder = fileRepository.findAllFileInFavoriteRoot(query.getAccountId(), typeOfFile, FavoriteShareConstants.FILE, pageable);
            var result = PageResultOf.of(listFileInRootFolder.getContent(),
                    query.getPage(),
                    listFileInRootFolder.getTotalElements(),
                    listFileInRootFolder.getTotalPages());
            return ResponseMessageOf.of(HttpStatus.OK,result);
        }


        var folderFavoriteContainDirectory = directoryRepository.getFolderFavoriteContainDirectory(query.getDirectoryId().toString(),
                query.getAccountId().toString(), FavoriteShareConstants.FOLDER);

        if (folderFavoriteContainDirectory.isEmpty()) {
            return ResponseMessageOf.ofBadRequest("This folder is not share with you!", Map.of());
        }

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "name"));
        var listFileResult = fileRepository.findAllByDirectoryId(query.getDirectoryId(), pageable);
        var result = PageResultOf.of(listFileResult.getContent(),
                query.getPage(),
                listFileResult.getTotalElements(),
                listFileResult.getTotalPages());
        return ResponseMessageOf.of(HttpStatus.OK, result);
    }
}

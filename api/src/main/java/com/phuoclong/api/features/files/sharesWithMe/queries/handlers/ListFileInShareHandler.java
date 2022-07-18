package com.phuoclong.api.features.files.sharesWithMe.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.sharesWithMe.queries.ListFileInShare;
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
@Component("ListFileInShareHandler")
public class ListFileInShareHandler implements Command.Handler<ListFileInShare, ResponseMessageOf<PageResultOf<FileEntity>>> {

    private DirectoryRepository directoryRepository;
    private FileRepository fileRepository;
    private AppUtil appUtil;

    @Override
    public ResponseMessageOf<PageResultOf<FileEntity>> handle(ListFileInShare query) {

        var directoryById = directoryRepository.findById(query.getDirectoryId());
        if(directoryById.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Folder not found!", Map.of());
        }

        var directory = directoryById.get();
        var fileType = appUtil.getFileTypeFromLevel(directory.getLevel());
        if (directory.getParentId().toString().equals(DirectoryConstant.ROOT_FOLDER)) {

            Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.ASC , "f.name"));
            var listFileInRootFolder = fileRepository.findAllFileInShareRoot(query.getAccountId(), fileType, FavoriteShareConstants.FILE, pageable);
            var result = PageResultOf.of(listFileInRootFolder.getContent(),
                                                            query.getPage(),
                                                            listFileInRootFolder.getTotalElements(),
                                                            listFileInRootFolder.getTotalPages());
            return ResponseMessageOf.of(HttpStatus.OK, result);

        }

        var folderShareContainDirectory = directoryRepository.getFolderShareContainDirectory(query.getDirectoryId().toString(),
                query.getAccountId().toString(), FavoriteShareConstants.FOLDER);

        if (folderShareContainDirectory.isEmpty()) {
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

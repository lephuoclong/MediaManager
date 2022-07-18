package com.phuoclong.api.features.files.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.commands.UploadFile;
import com.phuoclong.api.infrastructure.repositories.ContentRepository;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import com.phuoclong.api.infrastructure.services.AppUtil;
import com.phuoclong.api.infrastructure.services.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Component("UploadFileHandler")
public class UploadFileHandler implements Command.Handler<UploadFile, ResponseMessageOf<UUID>> {

    private FileStorageService storageService;
    private FileRepository fileRepository;
    private ContentRepository contentRepository;
    private DirectoryRepository directoryRepository;
    private AppUtil appUtil;

    @Override
    public ResponseMessageOf<UUID> handle(UploadFile command) {

        if(command.getMultipartFile().isEmpty()){
            return ResponseMessageOf.ofBadRequest(ResourceMessage.UPLOAD_FILE_IS_NULL, Map.of());
        }

        var directoryContainer = directoryRepository.findById(command.getDirectoryId());

        if (directoryContainer.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Folder no match", Map.of());
        }

        var lever = directoryContainer.get().getLevel();
        var levelAfterCheck = appUtil.getLevelFromFileType(Objects.requireNonNull(command.getMultipartFile().getContentType()));

        if (lever != levelAfterCheck) {
            return ResponseMessageOf.ofBadRequest(ResourceMessage.TYPE_OF_FILE_NOT_ACCEPT,
                    Map.of(UploadFile.Fields.multipartFile, ResourceMessage.TYPE_OF_FILE_NOT_ACCEPT));
        }

        try {
            var fileWithContent = storageService.store(command.getMultipartFile());
            if(fileWithContent == null){
                return ResponseMessageOf.of(HttpStatus.FORBIDDEN);
            }

            var fileUpload = fileWithContent.getFile();

            fileUpload.setAccountId(command.getAccountId());
            fileUpload.setDirectoryId(command.getDirectoryId());

            var file = fileRepository.saveAndFlush(fileUpload);
            var content = fileWithContent.getContent();
            content.setFileId(file.getId());
            contentRepository.saveAndFlush(content);

            return ResponseMessageOf.of(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseMessageOf.ofBadRequest("Upload file error", Map.of());
        }

    }

}

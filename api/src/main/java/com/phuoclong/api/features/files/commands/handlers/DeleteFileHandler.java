package com.phuoclong.api.features.files.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.commands.DeleteFile;
import com.phuoclong.api.infrastructure.repositories.ContentRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("DeleteFileHandler")
public class DeleteFileHandler implements Command.Handler<DeleteFile, ResponseMessageOf<String>> {

    private final FileRepository fileRepository;
    private final ContentRepository contentRepository;

    @Override
    public ResponseMessageOf<String> handle(DeleteFile command) {
        var fileDelete =fileRepository.findByIdAndAccountId(command.getId(), command.getAccountId());

        if (fileDelete.isEmpty()) {
            return ResponseMessageOf.ofBadRequest("Your role can not delete this file!",
                    Map.of(DeleteFile.Fields.id, "Your role can not delete this file!"));
        }

        var contentDelete = contentRepository.findByFileId(command.getId());

        fileRepository.deleteById(fileDelete.get().getId());

        if (contentDelete.isEmpty()) {
            return ResponseMessageOf.of(HttpStatus.ACCEPTED);
        }
        contentRepository.deleteById(contentDelete.get().getId());

        return ResponseMessageOf.of(HttpStatus.ACCEPTED);
    }
}

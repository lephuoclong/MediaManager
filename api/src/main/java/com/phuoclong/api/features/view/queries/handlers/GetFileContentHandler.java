package com.phuoclong.api.features.view.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.view.queries.GetFileContent;
import com.phuoclong.api.features.view.response.FileResponse;
import com.phuoclong.api.infrastructure.repositories.ContentRepository;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetFileContentHandler")
public class GetFileContentHandler implements Command.Handler<GetFileContent, ResponseEntity<FileResponse>> {

    private ContentRepository contentRepository;
    private FileRepository fileRepository;
    private DirectoryRepository directoryRepository;

    @Override
    public ResponseEntity<FileResponse> handle(GetFileContent query) {

        var file = fileRepository.findById(query.getFileId());

        if (file.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var fileEntity = file.get();
        var folderTree = directoryRepository.getShareFolderTree(fileEntity.getDirectoryId().toString(), query.getAccountId().toString());
        if (folderTree.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var content = contentRepository.findByFileId(query.getFileId());
        if(content.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        var fileDb = content.get().getContent();
           // content luu bằng byte[]
        var b64 = Base64.encodeBase64String(fileDb);
            // encode sang Base64
        var result = FileResponse.of(b64, fileEntity.getDisplayName(), fileEntity.getType());

        var headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", file.get().getDisplayName());
        headers.setCacheControl("no-cache");

        return new ResponseEntity<>(result, headers, HttpStatus.OK);


    }
}

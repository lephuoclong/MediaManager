package com.phuoclong.api.infrastructure.services;

import com.phuoclong.api.infrastructure.Entitis.ContentEntity;
import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.repositories.ContentRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.services.models.FileContent;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileStorageService {
    private final FileRepository fileRepository;
    private final ContentRepository contentRepository;

    public FileContent store(MultipartFile file) throws IOException {

        if(file == null){
            return null;
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        var fileEntity = new FileEntity();
        var contentFile = new ContentEntity();

        fileEntity.setName(fileName);
        fileEntity.setDisplayName(fileName);
        fileEntity.setSize(file.getSize());
        fileEntity.setType(file.getContentType());

        contentFile.setSize(file.getSize());
        contentFile.setContent(file.getBytes());
        contentFile.setType(file.getContentType());

        return new FileContent(fileEntity, contentFile);

    }

    public FileContent getFileWithContent(UUID fileId){
        var file = fileRepository.getById(fileId);
        var content = contentRepository.getByFileId(fileId);

        return new FileContent(file,content);
    }

//    public Collection<FileContent> getAllFileWithContent(){
//
//
//
//        return
//    }
}

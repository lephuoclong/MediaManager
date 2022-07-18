package com.phuoclong.api.infrastructure.services.models;

import com.phuoclong.api.infrastructure.Entitis.ContentEntity;
import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FileContent {
    FileEntity file;

    ContentEntity content;
}

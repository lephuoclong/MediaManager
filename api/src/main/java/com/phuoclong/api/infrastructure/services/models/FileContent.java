package com.phuoclong.api.infrastructure.services.models;

import com.phuoclong.api.infrastructure.Entitis.ContentEntity;
import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileContent {
    FileEntity file;

    ContentEntity content;
}

package com.phuoclong.api.infrastructure.services;

public interface AppUtil {
    String getFileTypeFromLevel(Integer level);
    Integer getLevelFromFileType(String contentType);
}

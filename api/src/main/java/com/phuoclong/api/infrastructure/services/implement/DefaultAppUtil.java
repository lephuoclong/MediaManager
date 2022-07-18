package com.phuoclong.api.infrastructure.services.implement;

import com.phuoclong.api.infrastructure.constant.FileTypeConstant;
import com.phuoclong.api.infrastructure.services.AppUtil;
import org.springframework.stereotype.Component;

@Component
public class DefaultAppUtil implements AppUtil {
    @Override
    public String getFileTypeFromLevel(Integer level) {
        switch (level){
            case 1:
                return FileTypeConstant.DOCUMENT;
            case 2:
                return FileTypeConstant.MUSIC;
            case 3:
                return FileTypeConstant.PHOTO;
            case 4:
                return FileTypeConstant.MOVIE;
            default:
                return null;
        }

    }

    @Override
    public Integer getLevelFromFileType(String contentType) {
        switch (contentType) {
            case FileTypeConstant.DOCUMENT:
                return 1;
            case FileTypeConstant.MUSIC:
                return 2;
            case FileTypeConstant.PHOTO:
                return 3;
            case FileTypeConstant.MOVIE:
                return 4;
            default:
                return null;
        }
    }
}

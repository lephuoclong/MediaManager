package com.phuoclong.api.features.files;

import com.phuoclong.api.infrastructure.controllers.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("files")
public class FileController extends BaseController {

    @PostMapping("/upload/{directoryId}")
    public ResponseEntity<?> uploadFileToDirectory(@Valid @RequestBody MultipartFile file, @PathVariable UUID directoryId){
        return null;
    }
}

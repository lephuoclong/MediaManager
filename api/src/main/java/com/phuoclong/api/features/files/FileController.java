package com.phuoclong.api.features.files;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.files.favorite.commands.AddFileToFavorite;
import com.phuoclong.api.features.files.favorite.commands.DeleteFileInFavorite;
import com.phuoclong.api.features.files.favorite.queries.GetListFileInFavorite;
import com.phuoclong.api.features.files.myShare.commands.OwnerRemoveFileInShare;
import com.phuoclong.api.features.files.myShare.queries.GetListFileInMyShare;
import com.phuoclong.api.features.files.sharesWithMe.commands.AddFileToShare;
import com.phuoclong.api.features.files.commands.DeleteFile;
import com.phuoclong.api.features.files.commands.UploadFile;
import com.phuoclong.api.features.files.query.GetListFileInDirectory;
import com.phuoclong.api.features.files.sharesWithMe.commands.DeleteFileInShareByCustomer;
import com.phuoclong.api.features.files.sharesWithMe.queries.ListFileInShare;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("files")
public class FileController extends BaseController {

    public FileController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @PostMapping("/upload/{directoryId}")
    public ResponseEntity<?> uploadFileToDirectory(@Valid @ModelAttribute UploadFile command,@PathVariable UUID directoryId){
        command.setDirectoryId(directoryId);
        return handleWithResponseMessage(command);
    }

    @GetMapping("/{directoryId}")
    public ResponseEntity<?> getListFileFromDirectory(@PathVariable UUID directoryId,
                                                      @RequestParam Integer page,
                                                      @RequestParam Integer pageSize){
        var query = GetListFileInDirectory.of(directoryId);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFileById(@PathVariable UUID id){
        var command = DeleteFile.of(id);
        return handleWithResponseMessage(command);
    }

    @PostMapping("/add-favorite")
    public ResponseEntity<?> addFavorite(@Valid @RequestBody AddFileToFavorite command){
        return handleWithResponseMessage(command);
    }

    @PostMapping("/add-to-share")
    public ResponseEntity<?> addToShare(@Valid @RequestBody AddFileToShare command){
        return handleWithResponseMessage(command);
    }

    @GetMapping("/shares-with-me/{directoryId}")
    public ResponseEntity<?> getListFileInShare(@PathVariable UUID directoryId,
                                                @RequestParam Integer page,
                                                @RequestParam Integer pageSize){
        var query = ListFileInShare.of(directoryId);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponseMessage(query);
    }

    @DeleteMapping("/customer-delete-share/{fileId}")
    public ResponseEntity<?> deleteFileInShareByCustomer(@PathVariable UUID fileId){

        var command = DeleteFileInShareByCustomer.of(fileId);
        return handleWithResponseMessage(command);
    }

    @GetMapping("/favorites/{directoryId}")
    public ResponseEntity<?> getListFileInFavorite(@PathVariable UUID directoryId,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer pageSize){
        var query = GetListFileInFavorite.of(directoryId);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponseMessage(query);
    }

    @DeleteMapping("/delete-favorites/{fileId}")
    public ResponseEntity<?> deleteFileInFavorite(@PathVariable UUID fileId){
        var command = DeleteFileInFavorite.of(fileId);
        return handleWithResponseMessage(command);
    }

    @GetMapping("/my-share/{parentId}")
    public ResponseEntity<?> getFilesInMyShare(@PathVariable UUID parentId){
        var query = GetListFileInMyShare.of(parentId);

        return handleWithResponse(query);
    }

    @DeleteMapping("/owner-remove-share/{fileId}")
    public ResponseEntity<?> deleteFileInMyShare(@PathVariable UUID fileId,
                                                 @RequestParam String receiverEmail){
        var command = new OwnerRemoveFileInShare();
        command.setFileId(fileId);
        command.setReceiverEmail(receiverEmail);
        return handleWithResponseMessage(command);
    }

}

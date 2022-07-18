package com.phuoclong.api.features.directories;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.directories.commands.*;
import com.phuoclong.api.features.directories.favorite.commands.AddDirectoryToFavorite;
import com.phuoclong.api.features.directories.favorite.commands.DeleteDirectoryFromFavorite;
import com.phuoclong.api.features.directories.favorite.queries.GetFavoriteTree;
import com.phuoclong.api.features.directories.favorite.queries.GetListFavorite;
import com.phuoclong.api.features.directories.myShare.queries.GetDirectoriesInMyShare;
import com.phuoclong.api.features.directories.myShare.queries.GetFolderTreeInMyShare;
import com.phuoclong.api.features.directories.queries.GetFolderTree;
import com.phuoclong.api.features.directories.queries.GetListDirectory;
import com.phuoclong.api.features.directories.sharesWithMe.commands.AddDirectoryToShare;
import com.phuoclong.api.features.directories.sharesWithMe.commands.CustomerDeleteDirectoryFromShare;
import com.phuoclong.api.features.directories.myShare.commands.OwnerDeleteDirectoryFromShare;
import com.phuoclong.api.features.directories.sharesWithMe.queries.GetDirectoryInfo;
import com.phuoclong.api.features.directories.sharesWithMe.queries.GetListShare;
import com.phuoclong.api.features.directories.sharesWithMe.queries.GetShareTree;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("directories")
public class DirectoryController extends BaseController {
    public DirectoryController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @PostMapping()
    public ResponseEntity<?> createDirectory(@Valid @RequestBody CreateDirectory command) {
        return handleWithResponseMessage(command);
    }

    @GetMapping("/info/{directoryId}")
    public ResponseEntity<?> getDirectoryInfo(@PathVariable UUID directoryId){
        var query = GetDirectoryInfo.of(directoryId);
        return handleWithResponse(query);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDirectory(@Valid @RequestBody UpdateDirectory command) {
        return handleWithResponseMessage(command);
    }

    @GetMapping("/{parentId}")
    public ResponseEntity<?> getListDirectoryByParentId( @PathVariable UUID parentId,
                                                        @RequestParam Integer page,
                                                        @RequestParam Integer pageSize) {
        var query = GetListDirectory.of(parentId);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("/folder-tree/{selectedId}")
    public ResponseEntity<?> getFolderTree(@PathVariable UUID selectedId){
        var query = GetFolderTree.of(selectedId);
        return handleWithResponse(query);
    }

    @DeleteMapping("/deleted/{directoryId}")
    public ResponseEntity<?> deletedFolder(@PathVariable UUID directoryId ){
        var command = DeleteDirectory.of(directoryId);
        return handleWithResponseMessage(command);
    }

    @PostMapping("/add-favorite")
    public ResponseEntity<?> addFavorite(@Valid @RequestBody AddDirectoryToFavorite command){
        return handleWithResponseMessage(command);
    }

    @DeleteMapping("/delete-favorite/{directoryId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable UUID directoryId){
        var command = DeleteDirectoryFromFavorite.of(directoryId);
        return handleWithResponseMessage(command);
    }

    @PostMapping("/add-to-share")
    public ResponseEntity<?> addShare(@Valid @RequestBody AddDirectoryToShare command){
        return handleWithResponseMessage(command);
    }

    @DeleteMapping("/customer-delete-share/{directoryId}")
    public ResponseEntity<?> customerDeleteShare( @PathVariable UUID directoryId){
        var command = CustomerDeleteDirectoryFromShare.of(directoryId);
        return handleWithResponseMessage(command);
    }

    @DeleteMapping("/owner-delete-share/{directoryId}")
    public ResponseEntity<?> ownerDeleteShare(@PathVariable UUID directoryId, @RequestParam String receiverEmail){
        var command = new OwnerDeleteDirectoryFromShare();
        command.setDirectoryId(directoryId);
        command.setReceiverEmail(receiverEmail);
        return handleWithResponseMessage(command);
    }

    @GetMapping("/shares-with-me/{directoryId}")
    public ResponseEntity<?> getListShare(@PathVariable UUID directoryId,
                                          @RequestParam Integer page,
                                          @RequestParam Integer pageSize){
        var query = GetListShare.of(directoryId);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("/share-tree/{directoryId}")
    public ResponseEntity<?> getFolderTreeInShare(@PathVariable UUID directoryId){
        var query = GetShareTree.of(directoryId);
        return handleWithResponseMessage(query);
    }

    @GetMapping("/favorite-tree/{directoryId}")
    public ResponseEntity<?> getFolderTreeInFavorite(@PathVariable UUID directoryId) {
        var query = GetFavoriteTree.of(directoryId);
        return handleWithResponse(query);
    }

    @GetMapping("/favorites/{directoryId}")
    public ResponseEntity<?> getListFavorite(@PathVariable UUID directoryId,
                                             @RequestParam Integer page,
                                             @RequestParam Integer pageSize){
        var query = GetListFavorite.of(directoryId);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("/my-share/{parentId}")
    public ResponseEntity<?> getDirectoriesInMyShare(@PathVariable UUID parentId){
        var query = GetDirectoriesInMyShare.of(parentId);
        return handleWithResponseMessage(query);
    }

    @GetMapping("/my-share-tree/{directoryId}")
    public ResponseEntity<?> getFolderTreeInMyShare(@PathVariable UUID directoryId,
                                                    @RequestParam UUID selectId){
        var query = new GetFolderTreeInMyShare();
        query.setDirectoryId(directoryId);
        query.setSelectId(selectId);
        return handleWithResponse(query);
    }
}

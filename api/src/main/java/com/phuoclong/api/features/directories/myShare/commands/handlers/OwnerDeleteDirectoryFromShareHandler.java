package com.phuoclong.api.features.directories.myShare.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.myShare.commands.OwnerDeleteDirectoryFromShare;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.repositories.ShareRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("OwnerDeleteDirectoryFromShareHandler")
public class OwnerDeleteDirectoryFromShareHandler implements Command.Handler<OwnerDeleteDirectoryFromShare, ResponseMessageOf<String>> {

    private DirectoryRepository directoryRepository;
    private AccountRepository accountRepository;
    private ShareRepository shareRepository;

    @Override
    public ResponseMessageOf<String> handle(OwnerDeleteDirectoryFromShare command) {

        var folderShare = directoryRepository.findById(command.getDirectoryId());

        if(folderShare.isEmpty()){
            return ResponseMessageOf.ofBadRequest("You are not owner this folder!", Map.of());
        }

        var accountReceiver = accountRepository.findByEmail(command.getReceiverEmail());

        if(accountReceiver.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Receiver is not exists!", Map.of());
        }

        var share = shareRepository.findByAccountIdAndFileIdAndShareName(accountReceiver.get().getId(),
                command.getDirectoryId(),
                FavoriteShareConstants.FOLDER);

        if (share.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Share is not exists!", Map.of());
        }

        shareRepository.deleteById(share.get().getId());

        return ResponseMessageOf.of(HttpStatus.ACCEPTED);
    }
}

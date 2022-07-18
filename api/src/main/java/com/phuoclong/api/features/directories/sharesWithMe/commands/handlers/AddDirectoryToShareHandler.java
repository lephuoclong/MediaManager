package com.phuoclong.api.features.directories.sharesWithMe.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.sharesWithMe.commands.AddDirectoryToShare;
import com.phuoclong.api.infrastructure.Entitis.ShareEntity;
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
@Component("AddDirectoryToShareHandler")
public class AddDirectoryToShareHandler implements Command.Handler<AddDirectoryToShare, ResponseMessageOf<String>> {

    private final DirectoryRepository directoryRepository;
    private final ShareRepository shareRepository;
    private final AccountRepository accountRepository;

    @Override
    public ResponseMessageOf<String> handle(AddDirectoryToShare command) {

        var accountByEmail = accountRepository.findByEmail(command.getEmailReceiver());

        if (accountByEmail.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Receiver email does not exists!",
                    Map.of(AddDirectoryToShare.Fields.emailReceiver, "Receiver email does not exists!"));
        }

        var accountReceiver = accountByEmail.get();

        var checkIsExistsShare = shareRepository.existsByAccountIdAndFileIdAndShareName(
                accountReceiver.getId(), command.getDirectoryId(), FavoriteShareConstants.FOLDER);
        if(Boolean.TRUE.equals(checkIsExistsShare)) {
            return ResponseMessageOf.ofBadRequest("You had been share this folder to this person",
                    Map.of(AddDirectoryToShare.Fields.directoryId, "You had been share this folder to this person"));
        }

        var folderShare = directoryRepository.findByIdAndAccountId(command.getDirectoryId(),command.getAccountId());

        if (folderShare.isEmpty()){
            return ResponseMessageOf.ofBadRequest("You are not the owner of this folder!",
                    Map.of(AddDirectoryToShare.Fields.directoryId, "You are not the owner of this folder!"));
        }

        if (folderShare.get().getAccountId().equals(accountReceiver.getId())){
            return ResponseMessageOf.ofBadRequest("You cannot share your folder for yourself!",
                    Map.of(AddDirectoryToShare.Fields.directoryId, "You cannot share your folder for yourself!"));
        }

        var share = new ShareEntity();

        share.setShareName(FavoriteShareConstants.FOLDER);
        share.setAccountId(accountReceiver.getId());
        share.setFileId(command.getDirectoryId());

        shareRepository.saveAndFlush(share);
        return ResponseMessageOf.of(HttpStatus.ACCEPTED,
                "Your folder was shared with " + accountReceiver.getEmail());
    }
}

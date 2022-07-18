package com.phuoclong.api.features.files.sharesWithMe.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.sharesWithMe.commands.AddDirectoryToShare;
import com.phuoclong.api.features.files.sharesWithMe.commands.AddFileToShare;
import com.phuoclong.api.infrastructure.Entitis.ShareEntity;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.repositories.ShareRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("AddFileToShareHandler")
public class AddFileToShareHandler implements Command.Handler<AddFileToShare, ResponseMessageOf<String>> {

    private AccountRepository accountRepository;
    private ShareRepository shareRepository;
    private FileRepository fileRepository;

    @Override
    public ResponseMessageOf<String> handle(AddFileToShare command) {
        var accountByEmail = accountRepository.findByEmail(command.getEmailReceiver());

        if (accountByEmail.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Receiver email does not exists!",
                    Map.of(AddFileToShare.Fields.emailReceiver, "Receiver email does not exists!"));
        }

        var accountReceiver = accountByEmail.get();

        var checkIsExistsShare = shareRepository.existsByAccountIdAndFileIdAndShareName(
                accountReceiver.getId(), command.getFileId(), FavoriteShareConstants.FILE);
        if(Boolean.TRUE.equals(checkIsExistsShare)) {
            return ResponseMessageOf.ofBadRequest("You had been share this file to this person",
                    Map.of(AddFileToShare.Fields.fileId, "You had been share this file to this person"));
        }

        var fileShare = fileRepository.findByIdAndAccountId(command.getFileId(),command.getAccountId());

        if (fileShare.isEmpty()){
            return ResponseMessageOf.ofBadRequest("You are not the owner of this file!",
                    Map.of(AddFileToShare.Fields.fileId, "You are not the owner of this file!"));
        }

        if (fileShare.get().getAccountId().equals(accountReceiver.getId())) {
            return ResponseMessageOf.ofBadRequest("You cannot share your file for yourself!",
                    Map.of(AddDirectoryToShare.Fields.directoryId, "You cannot share your file for yourself!"));
        }

        var share = new ShareEntity();

        share.setShareName(FavoriteShareConstants.FILE);
        share.setAccountId(accountReceiver.getId());
        share.setFileId(command.getFileId());

        shareRepository.saveAndFlush(share);
        return ResponseMessageOf.of(HttpStatus.ACCEPTED,
                "Your file was shared with " + accountReceiver.getEmail());
    }
}

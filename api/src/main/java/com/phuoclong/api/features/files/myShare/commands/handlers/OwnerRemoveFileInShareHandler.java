package com.phuoclong.api.features.files.myShare.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.myShare.commands.OwnerRemoveFileInShare;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.repositories.ShareRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("OwnerRemoveFileInShareHandler")
public class OwnerRemoveFileInShareHandler implements Command.Handler<OwnerRemoveFileInShare, ResponseMessageOf<String>> {

    private AccountRepository accountRepository;
    private ShareRepository shareRepository;

    @Override
    public ResponseMessageOf<String> handle(OwnerRemoveFileInShare command) {

        var accountReceiver = accountRepository.findByEmail(command.getReceiverEmail());

        if(accountReceiver.isEmpty()){
            return ResponseMessageOf.ofBadRequest("Receiver email is not correct!", Map.of());
        }

        var share = shareRepository.findByAccountIdAndFileIdAndShareName(accountReceiver.get().getId(),
                command.getFileId(), FavoriteShareConstants.FILE);

        if(share.isEmpty()){
            return ResponseMessageOf.ofBadRequest("This share is not exists!",
                    Map.of(OwnerRemoveFileInShare.Fields.fileId, "This share is not exists"));
        }

        shareRepository.deleteById(share.get().getId());

        return ResponseMessageOf.of(HttpStatus.ACCEPTED);
    }
}

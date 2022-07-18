package com.phuoclong.api.features.files.sharesWithMe.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.sharesWithMe.commands.DeleteFileInShareByCustomer;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.ShareRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("DeleteFileInShareByCustomerHandler")
public class DeleteFileInShareByCustomerHandler implements Command.Handler<DeleteFileInShareByCustomer, ResponseMessageOf<String>> {

    private ShareRepository shareRepository;

    @Override
    public ResponseMessageOf<String> handle(DeleteFileInShareByCustomer command) {

        var fileShare = shareRepository.findByAccountIdAndFileIdAndShareName(command.getAccountId(),
                command.getFileId(), FavoriteShareConstants.FILE);

        if(fileShare.isEmpty()){
            return ResponseMessageOf.ofBadRequest("This file is not share with you",
                    Map.of(DeleteFileInShareByCustomer.Fields.fileId, "This file is not share with you"));
        }

        shareRepository.deleteById(fileShare.get().getId());

        return ResponseMessageOf.of(HttpStatus.ACCEPTED, ResourceMessage.DELETE_SHARE_SUCCESS, Map.of(),ResourceMessage.DELETE_SHARE_SUCCESS);
    }
}

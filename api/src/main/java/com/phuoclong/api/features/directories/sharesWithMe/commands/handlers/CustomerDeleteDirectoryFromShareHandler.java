package com.phuoclong.api.features.directories.sharesWithMe.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.sharesWithMe.commands.CustomerDeleteDirectoryFromShare;
import com.phuoclong.api.features.directories.favorite.commands.DeleteDirectoryFromFavorite;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.ShareRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("CustomerDeleteDirectoryFromShareHandler")
public class CustomerDeleteDirectoryFromShareHandler implements Command.Handler<CustomerDeleteDirectoryFromShare, ResponseMessageOf<String>> {

    private final ShareRepository shareRepository;

    @Override
    public ResponseMessageOf<String> handle(CustomerDeleteDirectoryFromShare command) {

        var checkIsShareExist = shareRepository.findByAccountIdAndFileIdAndShareName(command.getAccountId(),
                command.getDirectoryId(),
                FavoriteShareConstants.FOLDER);

        if(checkIsShareExist.isPresent()){

            shareRepository.deleteById(checkIsShareExist.get().getId());

            return ResponseMessageOf.of(HttpStatus.ACCEPTED, ResourceMessage.DELETE_SHARE_SUCCESS, Map.of(),ResourceMessage.DELETE_SHARE_SUCCESS);
        }

        return ResponseMessageOf.ofBadRequest("This folder is not share with you",
                Map.of(DeleteDirectoryFromFavorite.Fields.directoryId, "This folder is not share with you"));
    }
}

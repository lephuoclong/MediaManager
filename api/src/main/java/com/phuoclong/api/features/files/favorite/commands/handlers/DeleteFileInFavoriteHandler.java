package com.phuoclong.api.features.files.favorite.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.favorite.commands.DeleteFileInFavorite;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.CategoryRepository;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("DeleteFileInFavoriteHandler")
public class DeleteFileInFavoriteHandler implements Command.Handler<DeleteFileInFavorite, ResponseMessageOf<String>> {

    private FileRepository fileRepository;
    private CategoryRepository categoryRepository;

    @Override
    public ResponseMessageOf<String> handle(DeleteFileInFavorite command) {

        var favorite = categoryRepository.findByAccountIdAndFileIdAndCategoryName(command.getAccountId(),command.getFileId(), FavoriteShareConstants.FILE);
        if(favorite.isPresent()){

            categoryRepository.deleteById(favorite.get().getId());

            return ResponseMessageOf.of(HttpStatus.ACCEPTED, ResourceMessage.DELETE_FAVORITE_SUCCESS, Map.of(),ResourceMessage.DELETE_FAVORITE_SUCCESS);
        }

        return ResponseMessageOf.ofBadRequest("This file is not share with you",
                Map.of(DeleteFileInFavorite.Fields.fileId, "This file is not share with you"));

    }
}

package com.phuoclong.api.features.directories.favorite.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.favorite.commands.DeleteDirectoryFromFavorite;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.CategoryRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("DeleteDirectoryFromFavoriteHandler")
public class DeleteDirectoryFromFavoriteHandler implements Command.Handler<DeleteDirectoryFromFavorite, ResponseMessageOf<String>> {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseMessageOf<String> handle(DeleteDirectoryFromFavorite command) {

        var checkIsFavoriteExist = categoryRepository.findByAccountIdAndFileIdAndCategoryName(command.getAccountId(),
                command.getDirectoryId(), FavoriteShareConstants.FOLDER );

        if(checkIsFavoriteExist.isPresent()){

            categoryRepository.deleteById(checkIsFavoriteExist.get().getId());

            return ResponseMessageOf.of(HttpStatus.ACCEPTED, ResourceMessage.DELETE_FAVORITE_SUCCESS, Map.of(),ResourceMessage.DELETE_FAVORITE_SUCCESS);
        }

        return ResponseMessageOf.ofBadRequest("This folder is not share with you",
                Map.of(DeleteDirectoryFromFavorite.Fields.directoryId, "This folder is not share with you"));
    }
}

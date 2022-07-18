package com.phuoclong.api.features.directories.favorite.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.directories.favorite.commands.AddDirectoryToFavorite;
import com.phuoclong.api.infrastructure.Entitis.CategoryEntity;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.CategoryRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("AddDirectoryToFavoriteHandler")
public class AddDirectoryToFavoriteHandler implements Command.Handler<AddDirectoryToFavorite, ResponseMessageOf<String>> {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseMessageOf<String> handle(AddDirectoryToFavorite command) {

        var checkIsFavoriteExist = categoryRepository.existsByAccountIdAndFileIdAndCategoryName(command.getAccountId(), command.getDirectoryId(), FavoriteShareConstants.FOLDER );

        if(Boolean.TRUE.equals(checkIsFavoriteExist)){
            return ResponseMessageOf.ofBadRequest( "Your favorite is exists!", Map.of());
        }

        var favorite = new CategoryEntity();

        favorite.setAccountId(command.getAccountId());
        favorite.setCategoryName(FavoriteShareConstants.FOLDER);
        favorite.setFileId(command.getDirectoryId());

        categoryRepository.saveAndFlush(favorite);

        return ResponseMessageOf.of(HttpStatus.ACCEPTED, "Add favorite is success!");
    }
}

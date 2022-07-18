package com.phuoclong.api.features.files.favorite.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.files.favorite.commands.AddFileToFavorite;
import com.phuoclong.api.infrastructure.Entitis.CategoryEntity;
import com.phuoclong.api.infrastructure.constant.FavoriteShareConstants;
import com.phuoclong.api.infrastructure.repositories.CategoryRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component("AddToFavoriteHandler")
public class AddFileToFavoriteHandler implements Command.Handler<AddFileToFavorite, ResponseMessageOf<String>> {

    private CategoryRepository categoryRepository;

    @Override
    public ResponseMessageOf<String> handle(AddFileToFavorite command) {

        var categoryIsExists = categoryRepository.existsByAccountIdAndFileIdAndCategoryName(
                command.getAccountId(),
                command.getFileId(),
                FavoriteShareConstants.FILE);
        if (Boolean.TRUE.equals(categoryIsExists)){
            return ResponseMessageOf.ofBadRequest( "Your favorite is exists!", Map.of());
        }

        var favorite = new CategoryEntity();

        favorite.setAccountId(command.getAccountId());
        favorite.setCategoryName(FavoriteShareConstants.FILE);
        favorite.setFileId(command.getFileId());

        categoryRepository.saveAndFlush(favorite);

        return ResponseMessageOf.of(HttpStatus.ACCEPTED, "Add favorite is success!");
    }
}

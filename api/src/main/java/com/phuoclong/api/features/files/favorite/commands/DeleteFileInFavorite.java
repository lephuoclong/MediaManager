package com.phuoclong.api.features.files.favorite.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@FieldNameConstants
@Data
public class DeleteFileInFavorite extends BaseIdentityCommand<ResponseMessageOf<String>> {

    UUID fileId;

}

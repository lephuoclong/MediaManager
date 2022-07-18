package com.phuoclong.api.features.directories.favorite.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@FieldNameConstants
public class DeleteDirectoryFromFavorite extends BaseIdentityCommand<ResponseMessageOf<String>> {

    UUID directoryId;

}

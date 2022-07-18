package com.phuoclong.api.features.directories.favorite.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddDirectoryToFavorite extends BaseIdentityCommand<ResponseMessageOf<String>> {

    UUID directoryId;

}

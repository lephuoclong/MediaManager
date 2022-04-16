package com.phuoclong.api.features.directories.commands;

import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;


@Setter
@Getter
@FieldNameConstants
public class UpdateDirectory extends BaseIdentityCommand<ResponseMessageOf<DirectoryEntity>> {

    UUID directoryId;

    String name;
}

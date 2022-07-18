package com.phuoclong.api.features.directories.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@Setter
@FieldNameConstants
public class CreateDirectory extends BaseIdentityCommand<ResponseMessageOf<String>> {
    String name;

    Integer level;

    UUID parentId;

}

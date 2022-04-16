package com.phuoclong.api.features.directories.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
public class CreateDirectory extends BaseIdentityCommand<ResponseMessageOf<String>> {
    String name;

    Integer level;

    UUID parentId;

}

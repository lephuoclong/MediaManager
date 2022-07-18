package com.phuoclong.api.features.directories.myShare.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Getter
@Setter
@FieldNameConstants
public class OwnerDeleteDirectoryFromShare extends BaseIdentityCommand<ResponseMessageOf<String>> {
    UUID directoryId;

    String receiverEmail;
}

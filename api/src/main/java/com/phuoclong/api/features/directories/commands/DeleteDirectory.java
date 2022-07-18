package com.phuoclong.api.features.directories.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
@AllArgsConstructor(staticName = "of")
public class DeleteDirectory extends BaseIdentityCommand<ResponseMessageOf<String>> {
    UUID directoryId;
}

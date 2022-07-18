package com.phuoclong.api.features.directories.sharesWithMe.commands;


import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
@FieldNameConstants
public class CustomerDeleteDirectoryFromShare extends BaseIdentityCommand<ResponseMessageOf<String>> {
    UUID directoryId;
}

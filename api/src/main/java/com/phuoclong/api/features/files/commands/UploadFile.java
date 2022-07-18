package com.phuoclong.api.features.files.commands;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
public class UploadFile extends BaseIdentityCommand<ResponseMessageOf<UUID>> {
    private UUID directoryId;

    @NotNull(message = "file is mandatory")
    private MultipartFile multipartFile;
}

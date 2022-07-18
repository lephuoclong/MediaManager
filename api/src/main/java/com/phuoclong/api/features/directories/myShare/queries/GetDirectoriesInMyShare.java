package com.phuoclong.api.features.directories.myShare.queries;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.models.MyFolderShare;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Data
public class GetDirectoriesInMyShare extends BaseIdentityCommand<ResponseMessageOf<Collection<MyFolderShare>>> {
    UUID parentId;
}

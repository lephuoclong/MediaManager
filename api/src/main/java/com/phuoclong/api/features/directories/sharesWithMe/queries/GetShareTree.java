package com.phuoclong.api.features.directories.sharesWithMe.queries;

import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class GetShareTree extends BaseIdentityCommand<ResponseMessageOf<Collection<FolderTrees>>> {
    UUID directoryId;
}

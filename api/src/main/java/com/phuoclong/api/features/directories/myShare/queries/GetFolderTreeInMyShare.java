package com.phuoclong.api.features.directories.myShare.queries;

import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@FieldNameConstants
public class GetFolderTreeInMyShare extends BaseIdentityCommand<ResponseEntity<Collection<FolderTrees>>> {

    UUID directoryId;
    UUID selectId;

}

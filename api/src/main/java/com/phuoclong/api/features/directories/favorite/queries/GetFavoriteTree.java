package com.phuoclong.api.features.directories.favorite.queries;

import com.phuoclong.api.features.directories.response.FolderTrees;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class GetFavoriteTree extends BaseIdentityCommand<ResponseEntity<Collection<FolderTrees>>> {

    UUID directoryId;

}

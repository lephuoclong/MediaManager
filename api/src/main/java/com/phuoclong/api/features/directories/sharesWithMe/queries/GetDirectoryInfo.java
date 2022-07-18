package com.phuoclong.api.features.directories.sharesWithMe.queries;

import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class GetDirectoryInfo extends BaseIdentityCommand<ResponseEntity<DirectoryEntity>> {
    UUID directoryId;
}

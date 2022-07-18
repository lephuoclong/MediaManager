package com.phuoclong.api.features.directories.sharesWithMe.queries;

import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class GetListShare extends PaginationCommand<ResponseEntity<PageResultOf<DirectoryEntity>>> {
    UUID directoryId;
}

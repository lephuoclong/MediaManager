package com.phuoclong.api.features.directories.queries;

import com.phuoclong.api.infrastructure.Entitis.DirectoryEntity;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class GetListDirectory extends PaginationCommand<ResponseEntity<PageResultOf<DirectoryEntity>>> {
    UUID parentId;
}

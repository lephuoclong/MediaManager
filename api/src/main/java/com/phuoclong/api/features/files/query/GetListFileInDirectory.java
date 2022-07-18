package com.phuoclong.api.features.files.query;

import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Data
public class GetListFileInDirectory extends PaginationCommand<ResponseEntity<PageResultOf<FileEntity>>> {
    UUID directoryId;
}

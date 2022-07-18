package com.phuoclong.api.features.files.sharesWithMe.queries;

import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "of")
public class ListFileInShare extends PaginationCommand<ResponseMessageOf<PageResultOf<FileEntity>>> {

    UUID directoryId;

}

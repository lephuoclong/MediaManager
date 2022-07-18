package com.phuoclong.api.features.files.favorite.queries;

import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class GetListFileInFavorite extends PaginationCommand<ResponseMessageOf<PageResultOf<FileEntity>>> {
    UUID directoryId;
}

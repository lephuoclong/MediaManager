package com.phuoclong.api.features.search.queries;

import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.models.FolderSearch;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class SearchFolder extends PaginationCommand<ResponseEntity<PageResultOf<FolderSearch>>> {
    String keySearch;
}

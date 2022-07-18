package com.phuoclong.api.features.search.queries;

import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.models.FileSearch;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class SearchMusic extends PaginationCommand<ResponseEntity<PageResultOf<FileSearch>>> {
    @Length(min = 3)
    String keySearch;

}

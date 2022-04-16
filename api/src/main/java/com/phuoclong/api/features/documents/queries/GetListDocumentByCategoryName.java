package com.phuoclong.api.features.documents.queries;

import com.phuoclong.api.infrastructure.Entitis.FileEntity;
import com.phuoclong.api.infrastructure.command.PaginationCommand;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import org.springframework.http.ResponseEntity;

public class GetListDocumentByCategoryName extends PaginationCommand<ResponseEntity<PageResultOf<FileEntity>>> {
}

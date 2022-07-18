package com.phuoclong.api.features.search.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.search.queries.SearchPhoto;
import com.phuoclong.api.infrastructure.constant.FileTypeConstant;
import com.phuoclong.api.infrastructure.models.FileSearch;
import com.phuoclong.api.infrastructure.repositories.FileRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("SearchPhotoHandler")
public class SearchPhotoHandler implements Command.Handler<SearchPhoto, ResponseEntity<PageResultOf<FileSearch>>> {
    private FileRepository fileRepository;
    @Override
    public ResponseEntity<PageResultOf<FileSearch>> handle(SearchPhoto query) {

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "name"));

        var listFileResult = fileRepository.searchFileWithKeySearch(query.getAccountId().toString(),
                FileTypeConstant.PHOTO, query.getKeySearch(), pageable);

        if(listFileResult.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        var result = PageResultOf.of(listFileResult.getContent(),
                query.getPage(), listFileResult.getTotalElements(), listFileResult.getTotalPages());

        return ResponseEntity.ok(result);
    }
}

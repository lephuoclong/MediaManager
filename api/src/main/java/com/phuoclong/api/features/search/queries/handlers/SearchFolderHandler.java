package com.phuoclong.api.features.search.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.search.queries.SearchFolder;
import com.phuoclong.api.infrastructure.constant.DirectoryConstant;
import com.phuoclong.api.infrastructure.constant.FileTypeConstant;
import com.phuoclong.api.infrastructure.models.FolderSearch;
import com.phuoclong.api.infrastructure.repositories.DirectoryRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component("SearchFolderHandler")
public class SearchFolderHandler implements Command.Handler<SearchFolder, ResponseEntity<PageResultOf<FolderSearch>>> {
    private DirectoryRepository directoryRepository;
    @Override
    public ResponseEntity<PageResultOf<FolderSearch>> handle(SearchFolder query) {

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "name"));

        var listFolderResult = directoryRepository.searchFolderWithKeySearch(query.getAccountId().toString(),
                query.getKeySearch(), UUID.fromString(DirectoryConstant.ROOT_FOLDER), pageable);

        if(listFolderResult.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        var result = PageResultOf.of(listFolderResult.getContent(), query.getPage(),
                listFolderResult.getTotalElements(), listFolderResult.getTotalPages());

        return ResponseEntity.ok(result);

    }
}

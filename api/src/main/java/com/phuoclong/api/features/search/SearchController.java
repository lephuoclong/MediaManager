package com.phuoclong.api.features.search;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.search.queries.*;
import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("search")
public class SearchController extends BaseController {
    public SearchController(Pipeline pipeline, AuthenticationManager authenticationManager, AccountService accountService) {
        super(pipeline, authenticationManager, accountService);
    }

    @GetMapping("document")
    public ResponseEntity<?> searchDocument(@RequestParam String keySearch,
                                            @RequestParam Integer page,
                                            @RequestParam Integer pageSize){
        var query = SearchDocument.of(keySearch);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("music")
    public ResponseEntity<?> searchMusic(@RequestParam String keySearch,
                                         @RequestParam Integer page,
                                         @RequestParam Integer pageSize){
        var query = SearchMusic.of(keySearch);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("photo")
    public ResponseEntity<?> searchPhoto(@RequestParam String keySearch,
                                         @RequestParam Integer page,
                                         @RequestParam Integer pageSize){
        var query = SearchPhoto.of(keySearch);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("movie")
    public ResponseEntity<?> searchMovie(@RequestParam String keySearch,
                                         @RequestParam Integer page,
                                         @RequestParam Integer pageSize){
        var query = SearchMovie.of(keySearch);
        query.setPage(page);
        query.setPageSize(pageSize);
        return handleWithResponse(query);
    }

    @GetMapping("folder")
    public ResponseEntity<?> searchFolder(@RequestParam String keySearch,
                                          @RequestParam Integer page,
                                          @RequestParam Integer pageSize){
        var query = SearchFolder.of(keySearch);
        query.setPage(page);
        query.setPageSize(pageSize);

        return handleWithResponse(query);
    }
}

package com.phuoclong.api.features.checkIn;

import an.awesome.pipelinr.Pipeline;
import com.phuoclong.api.features.checkIn.queries.CheckApi;
import com.phuoclong.api.infrastructure.controllers.BaseController;
import com.phuoclong.api.infrastructure.services.AuthenticationManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("check-in")
public class CheckInController extends BaseController {

    public CheckInController(Pipeline pipeline) {
        super(pipeline);
    }

    @GetMapping()
    public ResponseEntity<?> check(){
        var query = new CheckApi();
        System.out.println("okkokokok");
        return handle(query);
    }
}

package com.phuoclong.api.features.view.queries;

import com.phuoclong.api.features.view.response.FileResponse;
import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class GetFileContent extends BaseIdentityCommand<ResponseEntity<FileResponse>> {
    UUID fileId;
}

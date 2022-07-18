package com.phuoclong.api.features.files.myShare.queries;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.models.MyFileShare;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Data
public class GetListFileInMyShare extends BaseIdentityCommand<ResponseEntity<Collection<MyFileShare>>> {
    UUID parentId;
}

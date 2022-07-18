package com.phuoclong.api.features.account.queries;

import com.phuoclong.api.infrastructure.command.BaseIdentityCommand;
import com.phuoclong.api.infrastructure.models.AccountBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

@AllArgsConstructor(staticName = "of")
@Getter
@Setter
public class GetListAccount extends BaseIdentityCommand<ResponseEntity<Collection<AccountBase>>> {
    String keyword;
}

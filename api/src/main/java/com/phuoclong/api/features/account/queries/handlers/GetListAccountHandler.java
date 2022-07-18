package com.phuoclong.api.features.account.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.account.queries.GetListAccount;
import com.phuoclong.api.infrastructure.models.AccountBase;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;

@AllArgsConstructor
@Component("GetListAccountHandler")
public class GetListAccountHandler implements Command.Handler<GetListAccount, ResponseEntity<Collection<AccountBase>>> {
    private AccountRepository accountRepository;
    @Override
    public ResponseEntity<Collection<AccountBase>> handle(GetListAccount query) {

        var accountBase = accountRepository.getListAccountBaseByKeyword(query.getKeyword());

        if(accountBase.isEmpty()){

            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.ok(accountBase);
    }
}

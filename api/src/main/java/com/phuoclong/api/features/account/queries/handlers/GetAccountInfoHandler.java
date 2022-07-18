package com.phuoclong.api.features.account.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.account.queries.GetAccountInfo;
import com.phuoclong.api.infrastructure.models.AccountBase;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetAccountInfoHandler")
public class GetAccountInfoHandler implements Command.Handler<GetAccountInfo, ResponseMessageOf<AccountBase>> {

    private AccountRepository accountRepository;

    @Override
    public ResponseMessageOf<AccountBase> handle(GetAccountInfo query) {

        var accountBase = accountRepository.getAccountBase(query.getAccountId());

        if(accountBase.isEmpty()) {
            return ResponseMessageOf.of(HttpStatus.BAD_REQUEST);
        }

        return ResponseMessageOf.of(HttpStatus.OK, accountBase.get());
    }
}

package com.phuoclong.api.features.account.queries.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.account.queries.GetListAccount;
import com.phuoclong.api.infrastructure.models.AccountBase;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.response.PageResultOf;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component("GetListAccountHandler")
public class GetListAccountHandler implements Command.Handler<GetListAccount, ResponseEntity<PageResultOf<AccountBase>>> {
    private AccountRepository accountRepository;
    @Override
    public ResponseEntity<PageResultOf<AccountBase>> handle(GetListAccount query) {

        Pageable pageable = PageRequest.of(query.getPage(), query.getPageSize(), Sort.by(Sort.Direction.DESC, "firstName"));

        var accountBase = accountRepository.getListAccountBase(pageable);

        if(accountBase.isEmpty()){

            return ResponseEntity.noContent().build();

        }

        var result = PageResultOf.of(accountBase.getContent(),
                query.getPage(),
                accountBase.getTotalElements(),
                accountBase.getTotalPages());

        return ResponseEntity.ok(result);
    }
}

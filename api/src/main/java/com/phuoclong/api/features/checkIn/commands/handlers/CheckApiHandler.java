package com.phuoclong.api.features.checkIn.commands.handlers;

import an.awesome.pipelinr.Command;
import com.phuoclong.api.features.checkIn.commands.CheckApi;
import com.phuoclong.api.features.checkIn.response.CheckInResponse;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import com.phuoclong.api.infrastructure.resouce.ResourceMessage;
import com.phuoclong.api.infrastructure.response.ResponseMessageOf;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("CheckApiHandler")
@AllArgsConstructor
public class CheckApiHandler implements Command.Handler<CheckApi, ResponseMessageOf<CheckInResponse>> {

    private final AccountRepository accountRepository;

    @Override
    public ResponseMessageOf<CheckInResponse> handle(CheckApi command) {

        var accountFromDb = accountRepository.findById(command.getAccountId());

        if (accountFromDb.isEmpty() || accountFromDb.get().getStatus() != 1){
            return ResponseMessageOf.ofBadRequest(ResourceMessage.ACCOUNT_NOT_ACTIVE, Map.of());
        }

        var account = accountFromDb.get();
        var result = CheckInResponse.of(account.getId(),
                account.getAccount(),
                account.getFirstName().concat(" ").concat(account.getLastName()),
                account.getEmail());
        return ResponseMessageOf.of(HttpStatus.OK,result);
    }
}

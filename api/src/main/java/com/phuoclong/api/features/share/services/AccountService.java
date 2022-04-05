package com.phuoclong.api.features.share.services;

import com.phuoclong.api.infrastructure.Entitis.AccountEntity;

import java.util.UUID;

public interface AccountService {

    AccountEntity get(UUID accountId);
    AccountEntity update(AccountEntity account);

}

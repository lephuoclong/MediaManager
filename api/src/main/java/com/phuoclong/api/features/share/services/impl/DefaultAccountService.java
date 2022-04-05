package com.phuoclong.api.features.share.services.impl;

import com.phuoclong.api.features.share.services.AccountService;
import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(DefaultAccountService.class);

    @Override
    @Cacheable(cacheNames="accountEntity", key="#accountId")
    public AccountEntity get(UUID accountId) {

        logger.info("Trying to get account information for id {} ",accountId);
        var option=  accountRepository.findById(accountId);
        return option.orElse(null);

    }

    @Override
    @CacheEvict(cacheNames="accountEntity",allEntries=true,key = "#account.id")
    public AccountEntity update(AccountEntity account) {
        return accountRepository.saveAndFlush(account);
    }
}

package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.models.AccountBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    Boolean existsByEmail(String email);

    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByAccount(String username);

    Optional<AccountEntity> findByUsername(String username);

    Boolean existsByAccount(String username);

    Optional<AccountEntity> findByAccountAndPassword(String username, String password);

    @Query("SELECT ac.id as id, ac.account as username, ac.firstName as firstName, ac.lastName as lastName, ac.email as email FROM AccountEntity ac " +
            "WHERE ac.firstName LIKE %:keyword% OR ac.lastName LIKE %:keyword%")
    Collection<AccountBase> getListAccountBaseByKeyword(@Param("keyword") String keyword);

    @Query("SELECT ac.id as id, ac.account as username, ac.firstName as firstName, ac.lastName as lastName, ac.email as email FROM AccountEntity ac " +
            "WHERE ac.id = :accountId")
    Optional<AccountBase> getAccountBase(UUID accountId);
}

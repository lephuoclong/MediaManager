package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.AccountEntity;
import com.phuoclong.api.infrastructure.models.AccountBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    Boolean existsByEmail(String email);

    Optional<AccountEntity> findByEmail(String email);

    Optional<AccountEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<AccountEntity> findByUsernameAndPassword(String username, String password);

    @Query("SELECT ac.id as id, ac.username as username, ac.firstName as firstName, ac.lastName as lastName, ac.email as email FROM AccountEntity ac ")
    Page<AccountBase> getListAccountBase(Pageable pageable);
}

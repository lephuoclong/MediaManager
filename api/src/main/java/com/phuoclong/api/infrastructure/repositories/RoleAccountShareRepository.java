package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.RoleAccountShare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface RoleAccountShareRepository extends JpaRepository<RoleAccountShare, Long> {

    Collection<RoleAccountShare> findAllByAccountId(UUID accountId);
}

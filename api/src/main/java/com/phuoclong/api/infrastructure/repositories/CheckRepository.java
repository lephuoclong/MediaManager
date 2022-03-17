package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entity.CheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckRepository extends JpaRepository<CheckEntity, UUID> {
}

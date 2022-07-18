package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<ContentEntity, UUID> {

    ContentEntity getByFileId(UUID fileId);

    Optional<ContentEntity> findByFileId(UUID id);
}

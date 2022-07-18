package com.phuoclong.api.infrastructure.repositories;

import com.phuoclong.api.infrastructure.Entitis.ShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShareRepository extends JpaRepository<ShareEntity, Long> {
    Boolean existsByAccountIdAndFileIdAndShareName(UUID userId, UUID directoryId, String folder);

    Optional<ShareEntity> findByAccountIdAndFileIdAndShareName(UUID accountId, UUID directoryId, String folder);

    Optional<ShareEntity> findByFileIdAndShareName(UUID directoryId, String folder);
}
